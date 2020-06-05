# [Spring Boot 中 SPI 机制](http://www.iocoder.cn/Fight/SPI-mechanism-in-Spring-Boot/)

| [《Dubbo 实现原理与源码解析 —— 精品合集》](http://www.iocoder.cn/Dubbo/good-collection/?title) | [《Netty 实现原理与源码解析 —— 精品合集》](http://www.iocoder.cn/Netty/Netty-collection/?title) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [《Spring 实现原理与源码解析 —— 精品合集》](http://www.iocoder.cn/Spring/good-collection/?title) | [《MyBatis 实现原理与源码解析 —— 精品合集》](http://www.iocoder.cn/MyBatis/good-collection/?title) |
| [《Spring MVC 实现原理与源码解析 —— 精品合集》](http://www.iocoder.cn/Spring-MVC/good-collection/?title) | [《数据库实体设计合集》](http://www.iocoder.cn/Entity/good-collection/?title) |
| [《Spring Boot 实现原理与源码解析 —— 精品合集》](http://www.iocoder.cn/Spring-Boot/good-collection/?title) | [《Java 面试题 + Java 学习指南》](http://www.iocoder.cn/Interview/good-collection/?title) |

- [一、从java类加载机制说起](http://www.iocoder.cn/Fight/SPI-mechanism-in-Spring-Boot/)
- [二、spring中SPI机制实现](http://www.iocoder.cn/Fight/SPI-mechanism-in-Spring-Boot/)

------

------

# 一、从java类加载机制说起

- java中的类加载器负载加载来自文件系统、网络或者其他来源的类文件。jvm的类加载器默认使用的是双亲委派模式。三种默认的类加载器Bootstrap ClassLoader、Extension ClassLoader和System ClassLoader（Application ClassLoader）每一个中类加载器都确定了从哪一些位置加载文件。于此同时我们也可以通过继承java.lang.classloader实现自己的类加载器。

> Bootstrap ClassLoader：负责加载JDK自带的rt.jar包中的类文件，是所有类加载的父类
> Extension ClassLoader：负责加载java的扩展类库从jre/lib/ect目录或者java.ext.dirs系统属性指定的目录下加载类，是System ClassLoader的父类加载器
> System ClassLoader：负责从classpath环境变量中加载类文件

![java类加载结构](D:\workIdea\learn\img\9cff201fbfda8dde974cac6899746600.jpg)

### 1、双亲委派模型

- 原理：当一个类加载器收到类加载任务时，会先交给自己的父加载器去完成，因此最终加载任务都会传递到最顶层的BootstrapClassLoader，只有当父加载器无法完成加载任务时，才会尝试自己来加载。

> 具体：根据双亲委派模式，在加载类文件的时候，子类加载器首先将加载请求委托给它的父加载器，父加载器会检测自己是否已经加载过类，如果已经加载则加载过程结束，如果没有加载的话则请求继续向上传递直Bootstrap ClassLoader。如果请求向上委托过程中，如果始终没有检测到该类已经加载，则Bootstrap ClassLoader开始尝试从其对应路劲中加载该类文件，如果失败则由子类加载器继续尝试加载，直至发起加载请求的子加载器为止。

- 采用双亲委派模式可以保证类型加载的安全性，不管是哪个加载器加载这个类，最终都是委托给顶层的BootstrapClassLoader来加载的，只有父类无法加载自己猜尝试加载，这样就可以保证任何的类加载器最终得到的都是同样一个Object对象。

```java
protected Class<?> loadClass(String name, boolean resolve) {
    synchronized (getClassLoadingLock(name)) {
    // 首先，检查该类是否已经被加载，如果从JVM缓存中找到该类，则直接返回
    Class<?> c = findLoadedClass(name);
    if (c == null) {
        try {
            // 遵循双亲委派的模型，首先会通过递归从父加载器开始找，
            // 直到父类加载器是BootstrapClassLoader为止
            if (parent != null) {
                c = parent.loadClass(name, false);
            } else {
                c = findBootstrapClassOrNull(name);
            }
        } catch (ClassNotFoundException e) {}
        if (c == null) {
            // 如果还找不到，尝试通过findClass方法去寻找
            // findClass是留给开发者自己实现的，也就是说
            // 自定义类加载器时，重写此方法即可
           c = findClass(name);
        }
    }
    if (resolve) {
        resolveClass(c);
    }
    return c;
    }
}
```

### 2.双亲委派模型缺陷

- 在双亲委派模型中，子类加载器可以使用父类加载器已经加载的类，而父类加载器无法使用子类加载器已经加载的。这就导致了双亲委派模型并不能解决所有的类加载器问题。
- 案例：**Java 提供了很多服务提供者接口(Service Provider Interface，SPI)，允许第三方为这些接口提供实现。常见的 SPI 有 JDBC、JNDI、JAXP 等，这些SPI的接口由核心类库提供，却由第三方实现，这样就存在一个问题：SPI 的接口是 Java 核心库的一部分，是由BootstrapClassLoader加载的；SPI实现的Java类一般是由AppClassLoader来加载的。BootstrapClassLoader是无法找到 SPI 的实现类的，因为它只加载Java的核心库。它也不能代理给AppClassLoader，因为它是最顶层的类加载器。也就是说，双亲委派模型并不能解决这个问题**

### 3.使用线程上下文类加载器(ContextClassLoader)加载

- 如果不做任何的设置，Java应用的线程的上下文类加载器默认就是AppClassLoader。在核心类库使用SPI接口时，传递的类加载器使用线程上下文类加载器，就可以成功的加载到SPI实现的类。线程上下文类加载器在很多SPI的实现中都会用到。
- 通常我们可以通过Thread.currentThread().getClassLoader()和Thread.currentThread().getContextClassLoader()获取线程上下文类加载器。

### 4、使用类加载器加载资源文件，比如jar包

类加载器除了加载class外，还有一个非常重要功能，就是加载资源，它可以从jar包中读取任何资源文件，比如，ClassLoader.getResources(String name)方法就是用于读取jar包中的资源文件

```java
//获取资源的方法
public Enumeration<URL> getResources(String name) throws IOException {
    Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[2];
    if (parent != null) {
        tmp[0] = parent.getResources(name);
    } else {
        tmp[0] = getBootstrapResources(name);
    }
    tmp[1] = findResources(name);
    return new CompoundEnumeration<>(tmp);
}
```

它的逻辑其实跟类加载的逻辑是一样的，首先判断父类加载器是否为空，不为空则委托父类加载器执行资源查找任务，直到BootstrapClassLoader，最后才轮到自己查找。而不同的类加载器负责扫描不同路径下的jar包，就如同加载class一样，最后会扫描所有的jar包，找到符合条件的资源文件。

```java
// 使用线程上下文类加载器加载资源
public static void main(String[] args) throws Exception{
    // Array.class的完整路径
    String name = "java/sql/Array.class";
    Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(name);
    while (urls.hasMoreElements()) {
        URL url = urls.nextElement();
        System.out.println(url.toString());
    }
}
```

# 二、spring中SPI机制实现

### 1.SPI机制

（1）SPI思想

- SPI的全名为Service Provider Interface.这个是针对厂商或者插件的。
- SPI的思想：系统里抽象的各个模块，往往有很多不同的实现方案，比如日志模块的方案，xml解析模块、jdbc模块的方案等。面向的对象的设计里，我们一般推荐模块之间基于接口编程，模块之间不对实现类进行硬编码。一旦代码里涉及具体的实现类，就违反了可拔插的原则，如果需要替换一种实现，就需要修改代码。为了实现在模块装配的时候能不在程序里动态指明，这就需要一种服务发现机制。**java spi就是提供这样的一个机制：为某个接口寻找服务实现的机制**

（2）SPI约定

- 当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入。通过这个约定，就不需要把服务放在代码中了，通过模块被装配的时候就可以发现服务类了。

### 2、SPI使用案例

- common-logging apache最早提供的日志的门面接口。只有接口，没有实现。具体方案由各提供商实现， 发现日志提供商是通过扫描 META-INF/services/org.apache.commons.logging.LogFactory配置文件，通过读取该文件的内容找到日志提工商实现类。只要我们的日志实现里包含了这个文件，并在文件里制定 LogFactory工厂接口的实现类即可。

### 3、springboot中的类SPI扩展机制

- 在springboot的自动装配过程中，最终会加载META-INF/spring.factories文件，而加载的过程是由SpringFactoriesLoader加载的。从CLASSPATH下的每个Jar包中搜寻所有META-INF/spring.factories配置文件，然后将解析properties文件，找到指定名称的配置后返回。需要注意的是，其实这里不仅仅是会去ClassPath路径下查找，会扫描所有路径下的Jar包，只不过这个文件只会在Classpath下的jar包中。