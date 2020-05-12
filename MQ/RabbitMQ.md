# RabbitMQ

## 1、RabbitMQ简介

RabbitMQ是一种由Erlang开发的AMQP(Advanced Message Queue 

Protocel)的开源实现，AMQP 的出现其实也是应了广大人民群众的需求，虽然在同步    消息通讯的世界里有很多公开标准（如 COBAR的 IIOP ，或者是 SOAP 等），但是   在异步消息处理中却不是这样，只有大企业有一些商业实现（如微软的 MSMQ ，IBM  的 Websphere MQ 等），因此，在 2006 年的 6 月，Cisco 、Redhat、iMatix 等    联合制定了 AMQP 的公开标准。官网地址：http://www.rabbitmq.com

## 2、RabbitMQ工作原理

### a、RabbitMQ的基本组成

i.    Broker--简单来说就是消息队列服务器实体

ii.   Exchange--消息交换机，它指定消息按什么规则，路由到哪个队列

iii.   Queue--消息队列载体，每个消息都会被投入到一个或多个队列

iv.   Binding--绑定，它的作用就是把exchange和queue按照路由规则绑定起来

v.   Routing Key--路由关键字，exchange根据这个关键字进行消息投递

vi.   Channel--消息通道，在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务

vii.  Virtual Host--虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离

### b、Exchange类型有很多中，最常见的有direct、fanout、topic、

#### i.   direct（点对点）

路由规则：它会把消息路由到那些binding key与routing key完全匹配的Queue中

#### ii.   fanout（广播）  

路由规则：它会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中

#### iii.  Topic（发布/订阅）

路由规则：模糊匹配，可以通过通配符满足一部分规则就可以传送

1、routing key为一个句点号“. ”分隔的字符串，如“stock.usd.nyse”、    “nyse.vmw”、“quick.orange.rabbit”

2、binding key与routing key一样也是句点号“. ”分隔的字符串

3、binding key中可以存在两种特殊字符“*”与“#”，用于做模糊匹配， 其中“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）

#### iv.  Headers

路由规则：headers类型的Exchange不依赖于routing key与binding

key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性   进行匹配

v.    

## 3、RabbitMQ安装

### a、安装Erlang

RabbitMQ是使用Erlang开发，所以安装依赖Erlang，需要安装Erlang，

地址：http://www.erlang.org/downloads

### b、安装RabbitMQ

下载RabbitMQ安装包，地址：

https://www.rabbitmq.com/download.html

### c、安装后为RabbitMQ服务

![img](D:\workIdea\learn\img\rclip_image002.jpg)

### d、开始菜单里出现如下选项：

![img](D:\workIdea\learn\img\rclip_image003.png)

启动、停止、重新安装等。

### e、启用管理工具

进入C:\Program Files (x86)\RabbitMQ Server

\rabbitmq_server-3.4.1\sbin输入命令：

rabbitmq-plugins enable rabbitmq_management

这样就启动了管理工具，可以试一下命令：

停止：net stop RabbitMQ

启动：net start RabbitMQ

### f、登录管理页面

在浏览器中输入地址查看：http://127.0.0.1:15672/

![img](D:\workIdea\learn\img\rclip_image005.jpg)

使用默认账号登录：guest/ guest

### g、添加admin用户

![img](D:\workIdea\learn\img\rclip_image007.jpg)

### h、用户角色

1、超级管理员(administrator)

可登陆管理控制台，可查看所有的信息，并且可以对用户，策略(policy)进行操作。

2、监控者(monitoring)

可登陆管理控制台，同时可以查看rabbitmq节点的相关信息(进程数，内存使用情况，磁盘使用情况等)

3、策略制定者(policymaker)

可登陆管理控制台, 同时可以对policy进行管理。但无法查看节点的相关信息(上图红框标识的部分)。

4、普通管理者(management)

仅可登陆管理控制台，无法看到节点信息，也无法对策略进行管理。

5、其他

无法登陆管理控制台，通常就是普通的生产者和消费者。

### i、创建Virtual Hosts

![img](D:\workIdea\learn\img\rclip_image009.jpg)

### j、设置权限

![img](D:\workIdea\learn\img\rclip_image011.jpg)

选中admin，设置权限

![img](D:\workIdea\learn\img\rclip_image013.jpg)

查看权限已添加

![img](D:\workIdea\learn\img\rclip_image015.jpg)

k、 

## 4、Java调用RabbitMQ

### a、简单调用Demo

i.    导入jar包，amqp-client-3.4.1.jar，下载地址： 

https://www.rabbitmq.com/java-client.html

ii.   建立Sender发送消息，从管理界面查看：

![img](D:\workIdea\learn\img\rclip_image017.jpg)

![img](D:\workIdea\learn\img\rclip_image019.jpg)

iii.  点击上图队列名称，查看详情，并获取消息内容

![img](D:\workIdea\learn\img\rclip_image021.jpg)

iv.  消费者从队列中获取消息（example）

![img](D:\workIdea\learn\img\rclip_image023.jpg)

### b、Work模式的“能者多劳”，一个消息只能被一个消费者获取。（work）

![img](D:\workIdea\learn\img\rclip_image024.png)

### c、消息的确认模式

#### i.   自动确认

只要消息从队列中获取，无论消费者获取到消息后是否成功消息， 都认为是消息已经成功消费。

![img](D:\workIdea\learn\img\rclip_image026.jpg)

#### ii.   手动确认

消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态

![img](D:\workIdea\learn\img\rclip_image028.jpg)

### d、交换机的三种模式

#### i.   订阅模式(fanout)

解读：

1、1个生产者，多个消费者

2、每一个消费者都有自己的一个队列

3、生产者没有将消息直接发送到队列，而是发送到了交换机

4、每个队列都要绑定到交换机

5、生产者发送的消息，经过交换机，到达队列，实现，一个消息被多个消费者获取的目的

注意：一个消费者队列可以有多个消费者实例，只有其中一个消费者实例会消费

例子：【com.qk.rabbit.subscribe】包下，先启动Sender，再启动Recv

Sender

![img](D:\workIdea\learn\img\rclip_image030.jpg)

注意：消息发送到没有队列绑定的交换机时，消息将丢失，因为，交换机没有存储消息的能力，消息只能存在在队列中

 

 

Recv

![img](D:\workIdea\learn\img\rclip_image032.jpg)

在管理工具中查看队列和交换机的绑定关系：

![img](D:\workIdea\learn\img\rclip_image034.jpg)

![img](file:///C:\Users\ADMINI~1\AppData\Local\Temp\msohtmlclip1\01\clip_image036.jpg)

#### ii.   路由模式(direct)

Sender

![img](D:\workIdea\learn\img\rclip_image038.jpg)

Recv

![img](D:\workIdea\learn\img\rclip_image040.jpg)

Direct模式：Binding Key == Rounting Key

#### iii.  主题模式(通配符模式-topic)

Sender

![img](D:\workIdea\learn\img\rclip_image042.jpg)

Recv

![img](D:\workIdea\learn\img\rclip_image044.jpg)

### e、持久化交换机和队列

持久化：将交换机或队列的数据保存到磁盘，服务器宕机或重启之后依然存在。

非持久化：将交换机或队列的数据保存到内存，服务器宕机或重启之后将不存在。

![img](D:\workIdea\learn\img\rclip_image046.jpg)

f、 

 

## 5、Spring集成RabbitMQ

### a、Spring对AMQP支持，目前只做了RabbitMQ

### b、导入jar包列表

![img](D:\workIdea\learn\img\rclip_image048.jpg)

### c、Spring和RabbitMQ集成的主要jar包

![img](D:\workIdea\learn\img\rclip_image049.png)

### d、配置文件

![img](D:\workIdea\learn\img\rclip_image051.jpg)

![img](D:\workIdea\learn\img\rclip_image053.jpg)

### e、测试发送消息

![img](D:\workIdea\learn\img\rclip_image055.jpg)

## 6、RabbitMQ插件

a、插件管理的一些基本命令

![img](D:\workIdea\learn\img\rclip_image057.jpg)

i.    rabbitmq-plugins list-查看已安装的插件

![img](D:\workIdea\learn\img\rclip_image059.jpg)

ii.   rabbitmq-plugins enable/disable plugin-name --启动/禁用插件

b、插件是以(.ez)结尾的文件

c、插件存放目录路径配置，/sbin/rabbitmq-env.bat

![img](D:\workIdea\learn\img\rclip_image061.jpg)

d、插件的开发需要下载源码，准备环境(使用Erlang开发，暂缓)

e、 

## 7、RabbitMQ目录

![img](D:\workIdea\learn\img\rclip_image063.jpg)

a、ebin-RabbitMQ的运行库

b、escript

c、etc-配置目录

d、include

e、plugins--插件目录

插件是使用erlang开发，格式为.ez文件

f、priv

g、sbin-RabbitMQ的运行命令目录

h、 

## 8、总结

使用MQ实现商品数据的同步优势：

1、降低系统间耦合度

2、便于管理数据的同步（数据一致性）

## 9、思考

1、如何实现用户的动态创建？并将用户数据存储到数据库中？

用户可以不是RabbitMQ的用户，只需在连接、断开连接时监听则可

2、 

## 10、参考

1、https://www.cnblogs.com/frankyou/p/5283539.html

2、https://www.cnblogs.com/dwlsxj/p/RabbitMQ.html

3、https://blog.csdn.net/hellozpc/article/details/81436980

4、Spring集成RabbitMQ

https://spring.io/guides/gs/messaging-rabbitmq/

5、RabbitMQ插件

https://www.rabbitmq.com/plugins.html#enabled-plugins-file

https://www.rabbitmq.com/plugin-development.html

6、RabbitMQ源码

https://www.rabbitmq.com/build-server.html#prerequisites

https://github.com/rabbitmq

https://github.com/rabbitmq/rabbitmq-server-release

7、 

 

 

 

 

 