# ActiveMQ

## 1、ActiveMQ简介

ActiveMQ 是一个 MOM，具体来说是一个实现了 JMS 规范的系统间远程通信的消息代理。MOM是指面向消息的中间件，是用于以分布式应用或系统中的异步、松耦合、可靠、可扩展和安全通信的一类软件。MOM 的总体思想是它作为消息发送器和消息接收器之间的消息中介,这种中介提供了一个全新水平的松耦合。

JMS 叫做 Java 消息服务（Java Message Service）,是 Java 平台上有关面向 MOM 的技术规范，旨在通过提供标准的产生、发送、接收和处理消息的 API 简化企业应用的开发，类似于 JDBC 和关系型数据库通信方式的抽象。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image002.jpg)

官网地址：http://activemq.apache.org/

## 2、ActiveMQ工作原理

### a、基本概念

i.    Provider：纯 Java 语言编写的 JMS 接口实现（比如 ActiveMQ 就是）

ii.   Domains：消息传递方式，包括点对点（P2P）、发布/订阅（Pub/Sub）两种

iii.   Connection factory：客户端使用连接工厂来创建与 JMS provider 的连接

iv.   Destination：消息被寻址、发送以及接收的对象

 

v.    

### b、P2P和Pub/Sub

#### i.   P2P （点对点）

路由规则：消息域使用queue作为Destination，消息可以被同步或异步的发送和接收，每个消息只会给一个 Consumer 传送一次。Consumer 可以使用 MessageConsumer.receive() 同步地接收消息，也可以通过使用MessageConsumer.setMessageListener() 注册一个 MessageListener 实现异步接收。多个 Consumer 可以注册到同一个 queue 上，但一个消息只能被一个 Consumer 所接收，然后由该 Consumer 来确认消息。并且在这种情况下，Provider 对所有注册的 Consumer 以轮询的方式发送消息。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image004.jpg)

#### ii.   Pub/Sub（发布/订阅）

路由规则：消息域使用 topic 作为 Destination，发布者向 topic 发送消息，订阅者注册接收来自 topic 的消息。发送到 topic 的任何消息都将自动传递给所有订阅者。接收方式（同步和异步）与 P2P 域相同。除非显式指定，否则 topic 不会为订阅者保留消息。当然，这可以通过持久化（Durable）订阅来实现消息的保存。这种情况下，当订阅者与 Provider 断开时，Provider 会为它存储消息。当持久化订阅者重新连接时，将会受到所有的断连期间未消费的消息。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image006.jpg)

### c、ActiveMQ存储

ActiveMQ 在 queue 中存储 Message 时，采用先进先出顺序（FIFO）存储。同一时间一个消息被分派给单个消费者，且只有当 Message 被消费并确认时，它才能从存储中删除。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image008.jpg)

对于持久化订阅者来说，每个消费者获得 Message 的副本。为了节省存储空间，Provider 仅存储消息的一个副本。持久化订阅者维护了指向下一个 Message 的指针，并将其副本分派给消费者。以这种方式实现消息存储，因为每个持久化订阅者可能以不同的速率消费 Message，或者它们可能不是全部同时运行。此外，因每个 Message 可能存在多个消费者，所以在它被成功地传递给所有持久化订阅者之前，不能从存储中删除。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image010.jpg)

### d、ActiveMQ存储方式

#### i.   KahaDB

ActiveMQ 5.3 版本起的默认存储方式。KahaDB存储是一个基于文件的快速存储消息，设计目标是易于使用且尽可能快。它使用基于文件的消息数据库意味着没有第三方数据库的先决条件。

 

要启用 KahaDB 存储，需要在 activemq.xml 中进行以下配置：

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image012.jpg)

#### ii.   AMQ

与 KahaDB 存储一样，AMQ存储使用户能够快速启动和运行，因为它不依赖于第三方数据库。AMQ 消息存储库是可靠持久性和高性能索引的事务日志组合，当消息吞吐量是应用程序的主要需求时，该存储是最佳选择。但因为它为每个索引使用两个分开的文件，并且每个 Destination 都有一个索引，所以当你打算在代理中使用数千个队列的时候，不应该使用它。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image014.jpg)

#### iii.  JDBC

选择关系型数据库，通常的原因是企业已经具备了管理关系型数据的专长，但是它在性能上绝对不优于上述消息存储实现。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image016.jpg)

#### iv.  内存存储

内存消息存储器将所有持久消息保存在内存中。在仅存储有限数量 Message 的情况下，内存消息存储会很有用，因为 Message 通常会被快速消耗。在 activema.xml 中将 broker 元素上的 persistent 属性设置为 false 即可。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image018.jpg)

#### v.    

 

## 3、ActiveMQ安装

### a、安装ActiveMQ

下载ActiveMQ安装包，地址：

http://activemq.apache.org/components/classic/download/

### b、解压zip文件，目录如下

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image020.jpg)

### c、命令行cd到安装目录，启动ActiveMQ

启动失败，如下图，发现端口5672被占用，关闭RabbitMQ

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image022.jpg)

cd到{安装目录}/bin，执行activemq start,启动成功

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image024.jpg)

### d、登录管理页面

在浏览器中输入地址查看：http://127.0.0.1:8161/admin/

使用默认账号登录：admin/ admin

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image026.jpg)

### e、测试ActiveMQ的例子

发送消息-Sender

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image028.jpg)

管理页面查看

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image030.jpg)

开启一个接受端

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image032.jpg)

查看管理端

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image034.jpg)

发送消息，查看接受端是否接受

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image036.jpg)

f、 

## 4、ActiveMQ的部署方式

### a、单例模式

### b、无共享主从模式

这是最简单的 Provider 高可用性的方案，主从节点分别存储 Message。从节点需要配置为连接到主节点，并且需要特殊配置其状态。

 

所有消息命令（消息，确认，订阅，事务等）都从主节点复制到从节点，这种复制发生在主节点对其接收的任何命令生效之前。并且，当主节点收到持久消息，会等待从节点完成消息的处理（通常是持久化到存储），然后再自己完成消息的处理（如持久化到存储）后，再返回对 Producer 的回执。

 

从节点不启动任何传输，也不能接受任何客户端或网络连接，除非主节点失效。当主节点失效后，从节点自动成为主节点，并且开启传输并接受连接。这是，使用 failover 传输的客户端就会连接到该新主节点。

 

Broker 连接配置如下：

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image038.jpg)

但是，这种部署模式有一些限制，

 

主节点只会在从节点连接到主节点时复制其活动状态，因此当从节点没有连接上主节点之前，任何主节点处理的 Message 或者消息确认都会在主节点失效后丢失。不过你可以通过在主节点设置 waitForSlave 来避免，这样就强制主节点在没有任何一个从节点连接上的情况下接受连接。

就是主节点只能有一个从节点，并且从节点不允许再有其他从节点。

把正在运行的单例配置成无共享主从，或者配置新的从节点时，你都要停止当前服务，修改配置后再重启才能生效

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image039.png)

在可以接受一些故障停机时间的情况下，可以使用该模式。

 

从节点配置：

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image041.jpg)

此外，可以配置 shutdownOnMasterFailure 项，表示主节点失效后安全关闭，保证没有消息丢失，允许管理员维护一个新的从节点。

 

### c、共享存储主从模式

允许多个代理共享存储，但任意时刻只有一个是活动的。这种情况下，当主节点失效时，无需人工干预来维护应用的完整性。另外一个好处就是没有从节点数的限制。

 

有两种细分模式：

 

（1）基于数据库

 

它会获取一个表上的排它锁，以确保没有其他 ActiveMQ 代理可以同时访问数据库。其他未获得锁的代理则处于轮询状态，就会被当做是从节点，不会开启传输也不会接受连接。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image043.jpg)

（2）基于文件系统

 

需要获取分布式共享文件锁，linux 系统下推荐用 GFS2。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image045.jpg)

 

d、 

## 5、调用的通用步骤

1、获取连接工厂

2、使用连接工厂创建连接

3、启动连接

4、从连接创建会话

5、获取 Destination

6、创建 Producer，或

①   创建 Producer

②   创建 message

7、创建 Consumer，或发送或接收message发送或接收 message

①   创建 Consumer

②   注册消息监听器（可选）

8、发送或接收 message

9、关闭资源（connection, session, producer, consumer 等)

## 6、Java调用ActiveMQ

### a、简单调用Demo

导入jar包，jar包在{安装目录}/下

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image046.png)

查看运行界面监听的地址

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image048.jpg)

i.    建立Sender发送消息，从管理界面查看：

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image050.jpg)

队列TEST.FOO中一个消息

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image052.jpg)

ii.   建立接受者接受消息

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image054.jpg)

第二列正在接受的消息

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image056.jpg)

iii.   

 

### b、 

 

## 7、Spring集成ActiveMQ

### a、Spring官网对AMQP支持，目前只做了RabbitMQ,但是，ActiveMQ自身做了对Spring的支持

### b、导入jar包列表

 

### c、Spring和ActiveMQ集成的主要jar包

 

### d、配置文件

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image058.jpg)

![img](file:///C:\Users\ADMINI~1\AppData\Local\Temp\msohtmlclip1\01\clip_image060.jpg)

### e、测试发送消息

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image062.jpg)

## 8、ActiveMQ目录

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/clip_image064.jpg)

a、bin-启动目录

b、conf-配置目录

c、data

d、docs-文档目录

e、examples-demo目录

f、lib-java调用时引入jar目录

g、webapps

h、 

## 9、总结

使用MQ实现商品数据的同步优势：

1、降低系统间耦合度

2、便于管理数据的同步（数据一致性）

## 10、思考

1、如何实现用户的动态创建？并将用户数据存储到数据库中？

用户可以不是ActiveMQ的用户，只需在连接、断开连接时监听则可

2、 

## 11、参考

1、http://activemq.apache.org/components/classic/documentation

2、https://www.cnblogs.com/cyfonly/p/6380860.html

3、https://blog.csdn.net/qq_33404395/article/details/80590113

4、Spring集成ActiveMQ

 

5、ActiveMQ插件

 

6、ActiveMQ源码

7、 

 

 

 

 

 