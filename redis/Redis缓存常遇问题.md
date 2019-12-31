		设计缓存系统，必须要考虑的问题就是：缓存穿透、缓存击穿与失效时的雪崩效应。

- **缓存穿透**

  - **问题描述**

    ​		缓存穿透就是指查询一个一定不存在的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查询不到数据就不写入缓存，这就导致这个不存在的数据每次请求都要到存储层查询，失去了缓存的意义。

    ​		在流量大时，可能DB就挂掉了，要是有人利用不存在的Key频繁攻击我们的应用，这就是漏洞。

  - **解决方案**

    ​		最常见的解决方法就是采用布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被这个bitmap过滤掉，从而避免对底层存储系统的查询压力

    ​		另外还有一种简单粗暴的方法，如果查询返回的数据为空，我们仍然把这个空结果进行缓存，但是它的过期时间会很短，最长不超过5分钟。

- **缓存雪崩**	

  - **问题描述**

    ​		缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩。

  - **解决方案**

    ​		缓存失效时的雪崩效应对底层系统的冲击非常可怕。大多数系统设计者考虑用加锁或者队列的方式保证缓存的单线程写，从而避免失效时大量的并发请求落到底层的存储系统上。

    ​		一个简单的方案就是将缓存失效时间分散开，比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。

- **缓存击穿**

  - **问题描述**

    ​		对于一些设置了过期时间的Key，如果这些Key可能会在某些时间点被超高并发访问，是一种非常“热点”的数据。这个时候，这个时候需要考虑一个问题：缓存被击穿的问题。

    ​		缓存在某个时间点过期的时候，正好在这个时间点对这个Key缓存有大量的并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回射到缓存中，这个时候大并发的请求可能会瞬间把后端DB击垮。

  - **解决方案**

    1. **使用互斥锁（mutex key）**

       ​		业界比较常用的做法，是使用mutex。简单地说，就是在缓存失效时（判断拿出来的是空），不是立即去load db，而是先使用缓存工具的某些带成功的操作返回值得操作（比如redis的SETNX或者Memcache的ADD）去set一个mutex key，当操作返回成功时，再进行load db的操作并回设缓存；否则就重试整个get缓存的方法。

       SETNX，是[SET if Not exists]的缩写，也就是只有不存在的时候才设置，可以利用他来实现锁的效果，在redis2.6.1之前版本未实现setnx的过期时间，所以有两种版本代码

       ```java
       //2.6.1前单机版本锁
       String get(String key){
           String value = redis.get(key);
           if(value == null){
               if(redis.setnx(key_mutex,"1")){
                   //3 min timeout to avoid mutex holder crash
                   redis.expire(key_mutex,3*60);
                   value = db.get(key);
                   redis.set(key,value);
                   redis.delete(key_mutex);
               }else{
                   //其他线程休息50毫秒后重试
                   Thread.sleep(50);
                   get(key);
               }
           }
           return value;
       }
       ```

       新版本代码

       ```java
       public String get(key){
           String value = redis.get(key);
           if(value == null){
               if(redis.setnx(key_mutex,1,3*60) == 1){
                   value = db.get(key);
                   redis.set(key,value,expire_secs);
                   redis.del(key_mutex);
               }else{
                   Thread.sleep(50);
               	get(key);//重试
               }
           }
           
           return value;
           
       }
       ```

       memcache代码

       ```java
       v = memcache.get(key);
       if(v == null){
           if(memcache.add(key_mutex,3*60*1000) == true){
               value = db.get(key);
               memcache.set(key,value);
               memcache.delete(key_mutex);
           }else{
               Thread.sleep(50);
               retry();
           }
       }
       ```

       

    2. **“提前”使用互斥锁（mutex key）**

       ​		在value内部设置1个超时值（timeout1），timeout1比实际的memcache timeout(timeout2)小。

       ​		当存cache读取到timeout1发现它已经过期时，马上延长timeout1并重新设置到cache。然后再从数据库加载数据并设置到cache中。

       伪代码如下：

       ```java
       v = memcache.get(key);
       if(v == null){
           if(memcache.add(key_mutex,3*60*1000) == true){
               value = db.get(key);
               memcache.set(key,value);
               memcache.delete(key_mutex);
           }else{
               Thread.sleep(50);
               retry();
           }
       }else{
           if(v.timeout <= now()){
               if(memcache.add(key_mutex,3*60*1000) == true){
                   v.timeout = 3*60*1000 ；
                   memcache.set(key,v,KEY_TIMEOUT*2);
                  
                   value = db.get(key);
                   v.timeout = KEY_TIMEOUT ；
                   memcache.set(key,value,KEY_TIMEOUT*2);
                   memcache.delete(key_mutex);
                   
               }else{
                   Thread.sleep(50);
                   retry();
               }
           }else{
               Thread.sleep(50);
               retry();
           }
       }
       ```

       

    3. **“永远不过期”**

        这里的“永远不过期”包含两层含义：

       1. 从redis上看，确实没有设置过期时间，这就保证了，不会出现热点key过期问题，也就是“物理”不过期。
     2. 从功能上看，如果不过期，那不就成静态的了吗？所以我们把过期时间存在key的对应value里，如果发现要过期，通过一个后台异步线程进行缓存的构建，就是“逻辑过期”。
  
     从实战看，这种方法对于性能非常友好，唯一不足的就是构建缓存的时候，其余线程（非构建缓存的线程）可能访问的是老数据，但是对于一般的互联网功能还是可以忍受的。
  
       ```java
       String get(final String key){
           V v= redis.get(Key);
           String value = v.getValue();
           long timeout = v.getTimeout();
           if(v.timeout <= System.currentTimeMillis()){
               //异步更新后台异常执行
               threadPool.execute(new Runnable(){
                   public void run(){
                       String keyMutex = "mutex:" + key;
                       if(redis.setinx(keyMutex,"1")){
                           redis.expire(keyMutex,"1");
                           String  dbValue = db.get(key);
                           redis.set(key,dbValue);
                           redis.delete(keyMutex);
                       }
                   }
               });
           }
       }
       ```
  
       
  
    4. **资源保护**
  
       采用netflix的hystrix，可以做资源的隔离保护主线程池，如果把这个应用到缓存的构建中未尝不可。
    
      四种解决方案：没有最佳只有最合适
    
      解决方案												优点													缺点
    
      简单分布式（Tim yan）		1、思路简单；2、保证一致性			1、代码复杂度增大，2存在死锁的																												风险‘3、存在线程池阻塞的风险
    
      加另外一个过期时间				1、保证一致性									同上
    
      （Tim  yang）
    
      
    
      不过期										1、异步构建缓存，不会阻塞线		1、不保证一致性
    
      ​													程池					                                 2、代码复杂度增大（每个value都																													要维护一个timekey）
    
      ​																												3、占用一定的内存空间（每个																												value都要维护一个timekey）
    
      资源隔离组件hystrix				1、hystrix技术成熟，有效保证		部分访问存在降级策略
    
      ​													后端																						
  
  - ​	总结
  
    - 针对业务系统，永远都是具体情况具体分析，没有最好，只有更合适。
    - 最后，对于缓存系统常见的缓存满了和数据丢失问题，需要根据具体业务分析，通常我们采用LRU测试处理溢出，Redis的RDB和AOF持久化策略来保证一定情况下的数据安全。

