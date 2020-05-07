synchronized是一个重量级锁，相对于Lock，显得很笨重。

随后Java SE 1.6之后对synchronized做了一系列的优化，显得不那么笨重了。我们这里将讨论synchronized的**实现机制**，Java对它做了**哪些优化**、**锁优化机制**、**锁的存储结构**和**升级过程**。

#### synchronized的实现原理：

​	synchronized可以保证方法或者代码块在运行时，只有一个方法可以进入到临界区，同时还可以保证共享变量的内存可见性。

Java中每个对象可以作为锁，是synchronized实现同步的基础：

1. 普通同步方法，锁的是当前的实例对象
2. 静态同步方法，锁的是当前类的class对象
3. 同步方法块，锁的是方法块里的对象



#### synchronized的实现机制

​	一个线程进访问同步代码块时，首先需得到锁才能执行同步代码，当退出或者异常时，必须释放锁。

​	这是怎么实现的呢？

```java
package com.qiaok.concurrence.syn;

/**
 * synchronized实现机制
 *
 * @since 2020-05-07
 * @author qiaok
 */
public class SynchronizedTest {
    /**
     * 同步方法
     */
    public synchronized void  test(){

    }

    /**
     * 同步代码块
     */
    public void test2(){
        synchronized(this){

        }
    }
}

```

利用javap工具查看生成的class文件信息，分析synchronized的实现机制。**(JDK1.7)**

```shell
javap -v SynchronizedTest.class
Classfile /D:/workIdea/learn/并发/concurrence/src/main/java/com/qiaok/concurrence/syn/SynchronizedTest.class
  Last modified 2020-5-7; size 437 bytes
  MD5 checksum 4639ab8c97242fa144ecffef40161f5c
  Compiled from "SynchronizedTest.java"
public class com.qiaok.concurrence.syn.SynchronizedTest
  SourceFile: "SynchronizedTest.java"
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#16         //  java/lang/Object."<init>":()V
   #2 = Class              #17            //  com/qiaok/concurrence/syn/SynchronizedTest
   #3 = Class              #18            //  java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               test
   #9 = Utf8               test2
  #10 = Utf8               StackMapTable
  #11 = Class              #17            //  com/qiaok/concurrence/syn/SynchronizedTest
  #12 = Class              #18            //  java/lang/Object
  #13 = Class              #19            //  java/lang/Throwable
  #14 = Utf8               SourceFile
  #15 = Utf8               SynchronizedTest.java
  #16 = NameAndType        #4:#5          //  "<init>":()V
  #17 = Utf8               com/qiaok/concurrence/syn/SynchronizedTest
  #18 = Utf8               java/lang/Object
  #19 = Utf8               java/lang/Throwable
{
  public com.qiaok.concurrence.syn.SynchronizedTest();
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 9: 0

  public synchronized void test();
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LineNumberTable:
        line 15: 0

  public void test2();
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter
         4: aload_1
         5: monitorexit
         6: goto          14
         9: astore_2
        10: aload_1
        11: monitorexit
        12: aload_2
        13: athrow
        14: return
      Exception table:
         from    to  target type
             4     6     9   any
             9    12     9   any
      LineNumberTable:
        line 21: 0
        line 23: 4
        line 24: 14
      StackMapTable: number_of_entries = 2
           frame_type = 255 /* full_frame */
          offset_delta = 9
          locals = [ class com/qiaok/concurrence/syn/SynchronizedTest, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
           frame_type = 250 /* chop */
          offset_delta = 4
```

从上面可以看出，**同步代码块使用 monitorenter和monitorexit指令实现**，**同步方法是用ACC_SYNCHRONIZED实现**。

**同步代码块**：monitorenter指令插入到同步代码块的开始位置，monitorexit指令插入到同步代码块的结束位置，JVM需要保证每一个monitorenter都有一个monitorexit与之相对应。任何对象都有一个monitor与之相关联，当且一个monitor被持有之后，他将处于锁定状态。线程执行到monitorenter指令时，将会尝试获取对象所对应的monitor所有权，即尝试获取对象的锁；

**同步方法**：synchronized方法则会被翻译成普通的方法调用和返回指令如:invokevirtual、areturn指令，在VM字节码层面并没有任何特别的指令来实现被synchronized修饰的方法，而是在Class文件的方法表中将该方法的accessflags字段中的synchronized标志位置1，表示该方法是同步方法并使用调用该方法的对象或该方法所属的Class在JVM的内部对象表示Klass做为锁对象。



#### 锁优化

​	jdk1.6对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。 锁主要存在四中状态，依次是：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，他们会随着竞争的激烈而逐渐升级。注意锁可以升级不可降级，这种策略是为了提高获得锁和释放锁的效率。

##### 自旋锁

​	线程的阻塞和唤醒需要CPU从用户态转为核心态，频繁的阻塞和唤醒对CPU负担很重，势必给系统的并发性能带来很大的压力。同时我们发现对象锁只会持续很短时间，所以引入自旋锁

​	所谓自旋锁，就是让该线程等待一段时间，不会被立即挂起，看持有锁的线程是否会很快释放锁。执行一段无意义的循环即可（自旋）。

​	自旋锁在JDK 1.4.2中引入，默认关闭，但是可以使用-XX:+UseSpinning开开启，在JDK1.6中默认开启。同时自旋的默认次数为10次，可以通过参数-XX:PreBlockSpin来调整； 

##### 适应性自旋锁

​	JDK 1.6引入了更加聪明的自旋锁，即自适应自旋锁。所谓自适应就意味着自旋的次数不再是固定的，它是由前一次在同一个锁上的自旋时间及锁的拥有者的状态来决定。它怎么做呢？线程如果自旋成功了，那么下次自旋的次数会更加多，因为虚拟机认为既然上次成功了，那么此次自旋也很有可能会再次成功，那么它就会允许自旋等待持续的次数更多。反之，如果对于某个锁，很少有自旋能够成功的，那么在以后要或者这个锁的时候自旋的次数会减少甚至省略掉自旋过程，以免浪费处理器资源。

##### 锁消除

​		有些情况下，JVM检测到不可能存在共享数据竞争，这是JVM会对这些同步锁进行锁消除。锁消除的依据是逃逸分析的数据支持。

   如果不存在竞争，为什么还需要加锁呢？所以锁消除可以节省毫无意义的请求锁的时间。变量是否逃逸，对于虚拟机来说需要使用数据流分析来确定，但是对于我们程序员来说这还不清楚么？我们会在明明知道不存在数据竞争的代码块前加上同步吗？但是有时候程序并不是我们所想的那样？我们虽然没有显示使用锁，但是我们在使用一些JDK的内置API时，如StringBuffer、Vector、HashTable等，这个时候会存在隐形的加锁操作。

##### 锁粗化

​	我们知道在使用同步锁的时候，需要让同步块的作用范围尽可能小—仅在共享数据的实际作用域中才进行同步，这样做的目的是为了使需要同步的操作数量尽可能缩小，如果存在锁竞争，那么等待锁的线程也能尽快拿到锁。

   在大多数的情况下，上述观点是正确的，LZ也一直坚持着这个观点。但是如果一系列的连续加锁解锁操作，可能会导致不必要的性能损耗，所以引入锁粗话的概念。

   锁粗话概念比较好理解，就是将多个连续的加锁、解锁操作连接在一起，扩展成一个范围更大的锁。如上面实例：vector每次add的时候都需要加锁操作，JVM检测到对同一个对象（vector）连续加锁、解锁操作，会合并一个更大范围的加锁、解锁操作，即加锁解锁操作会移到for循环之外。

##### 轻量级锁

​		引入轻量级锁的主要目的是在多没有多线程竞争的前提下，减少传统的重量级锁使用操作系统互斥量产生的性能消耗。当关闭偏向锁功能或者多个线程竞争偏向锁导致偏向锁升级为轻量级锁，则会尝试获取轻量级锁，

​	**获取锁**

1．   判断当前对象是否处于无锁状态（hashcode、0、01），若是，则JVM首先将在当前线程的栈帧中建立一个名为锁记录（Lock Record）的空间，用于存储锁对象目前的Mark Word的拷贝（官方把这份拷贝加了一个Displaced前缀，即Displaced Mark Word）；否则执行步骤（3）；

2．   JVM利用CAS操作尝试将对象的Mark Word更新为指向Lock Record的指正，如果成功表示竞争到锁，则将锁标志位变成00（表示此对象处于轻量级锁状态），执行同步操作；如果失败则执行步骤（3）；

3．   判断当前对象的Mark Word是否指向当前线程的栈帧，如果是则表示当前线程已经持有当前对象的锁，则直接执行同步代码块；否则只能说明该锁对象已经被其他线程抢占了，这时轻量级锁需要膨胀为重量级锁，锁标志位变成10，后面等待的线程将会进入阻塞状态；

**释放锁**轻量级锁的释放也是通过CAS操作来进行的，主要步骤如下：

1. 取出在获取轻量级锁保存在Displaced Mark Word中的数据；

2. 用CAS操作将取出的数据替换当前对象的Mark Word中，如果成功，则说明释放锁成功，否则执行（3）；

3. 如果CAS操作替换失败，说明有其他线程尝试获取该锁，则需要在释放锁的同时需要唤醒被挂起的线程。

对于轻量级锁，其性能提升的依据是“对于绝大部分的锁，在整个生命周期内都是不会存在竞争的”，如果打破这个依据则除了互斥的开销外，还有额外的CAS操作，因此在有多线程竞争的情况下，轻量级锁比重量级锁更慢；

![image-20200507104137983](D:\workIdea\learn\img\image-20200507104137983.png)

##### 偏向锁

引入偏向锁主要目的是：为了在无多线程竞争的情况下尽量减少不必要的轻量级锁执行路径。上面提到了轻量级锁的加锁解锁操作是需要依赖多次CAS原子指令的。那么偏向锁是如何来减少不必要的CAS操作呢？我们可以查看Mark work的结构就明白了。只需要检查是否为偏向锁、锁标识为以及ThreadID即可，处理流程如下：

**获取锁**

1. 检测Mark Word是否为可偏向状态，即是否为偏向锁1，锁标识位为01；

2. 若为可偏向状态，则测试线程ID是否为当前线程ID，如果是，则执行步骤（5），否则执行步骤（3）；

3. 如果线程ID不为当前线程ID，则通过CAS操作竞争锁，竞争成功，则将Mark Word的线程ID替换为当前线程ID，否则执行线程（4）；

4. 通过CAS竞争锁失败，证明当前存在多线程竞争情况，当到达全局安全点，获得偏向锁的线程被挂起，偏向锁升级为轻量级锁，然后被阻塞在安全点的线程继续往下执行同步代码块；

5. 执行同步代码块

**释放锁**

​	偏向锁的释放采用了一种只有竞争才会释放锁的机制，线程是不会主动去释放偏向锁，需要等待其他线程来竞争。偏向锁的撤销需要等待全局安全点（这个时间点是上没有正在执行的代码）。其步骤如下：

1. 暂停拥有偏向锁的线程，判断锁对象石是否还处于被锁定状态；
2. 撤销偏向苏，恢复到无锁状态（01）或者轻量级锁的状态；

![image-20200507104112385](D:\workIdea\learn\img\image-20200507104112385.png)

##### 重量级锁

​	重量级锁通过对象内部的监视器（monitor）实现，其中monitor的本质是依赖于底层操作系统的Mutex Lock实现，操作系统实现线程之间的切换需要从用户态到内核态的切换，切换成本非常高。