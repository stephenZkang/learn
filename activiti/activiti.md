1. #   Activiti流程引擎配置

   ![image-20191202154143329](D:\study\activiti\images\image-20191202154143329.png)

   - ###### 流程引擎配置类（ProcessEngineConfiguration）

     - 查找并解析xml配置文件activiti.cfg.xml

       - activiti.cfg.xml

         ![](D:\study\activiti\images\image-20191202154500248.png)

     - 提供多个静态方法创建配置对象

       - 静态方法创建配置对象

         ![image-20191202154611520](D:\study\activiti\images\image-20191202154611520.png)

     - 实现几个基于不同场景的子类，配置方式非常灵活

       - 多个子类适应不同的场景

         ![image-20191202154837056](D:\study\activiti\images\image-20191202154837056.png)

         - ProcessEngineConfiguration	--	抽象类 - 定义了大量的常量
           - ProcessEngineConfigurationImpl	--	抽象类 - 定义了初始化工作
             - StandaloneProcessEngineConfiguration  -- 独立基于内存的流程引擎配置对象，不依赖spring
             - SpringProcessEngineConfiguration - 根据资源文件加载会通过spring解析，依赖spring

   - 根据helloworld工程构建脚手架，并安装

   - 数据库配置

     - 缺省默认配置，使用H2内存数据库

     - 配置JDBC属性，使用mybatis提供的连接池

       ![image-20191203102927233](D:\study\activiti\images\image-20191203102927233.png)

     - 配置DataSource

       ![image-20191203103238918](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20191203103238918.png)

       - Druid数据库配置

         ![image-20191203103319959](D:\study\activiti\images\image-20191203103319959.png)

         ![image-20191203103340127](D:\study\activiti\images\image-20191203103340127.png)

     - 配置databaseType

       ![image-20191203103636928](D:\study\activiti\images\image-20191203103636928.png)

     - 数据库更新策略

       ![image-20191203103557000](D:\study\activiti\images\image-20191203103557000.png)

       

   - 日志和数据记录配置

     - 日志组件的关系及MDC

       - 日志组件

         ![image-20191203141031359](D:\study\activiti\images\image-20191203141031359.png)

         logback性能10倍于Log4j，Log4j的作者又开发的logback

         改变依赖可以将spring中原有的commons-logging覆盖为slf4j

       - 配置开启MDC（Mapped Diagnostic Contexts）

         1. 默认没有开启，手动设置LogMDC.setMDCEnable(true)
         2. 配置logback.xml 日志模板%X {mdcProcessInstanceID}
         3. 流程只有在执行过程中出现异常时才会记录MDC信息 

     - 配置历史记录级别（HistoryLevel）

       - none：不记录历史流程，性能高，流程结束后不可读取
       - activiti：归档流程实例和活动实例
       - audit：默认值，在activiti基础上同步变量值，保存表单属性
       - full:性能较差，记录所有实例和变量细节的变化

     - 配置基于db的事件日志（Event logging）

       - 实验性的事件记录机制，性能影响较大
       - 开启默认记录所有数据的变化过程，表记录快速增长
       - 日志内容格式JSON格式，建议存入MongoDB

     - 事件日志配置启动

       ```<property name="enableDatabaseEventLogging" value="true" />
       
       - EventLogger的架构挺有意思

         ```TXT
    EventLogger
         EventLoggerEventHandler
         CommandContext
         EventFlusher
       ```
       
     - 事件及监听器配置

       - 事件及监听器原理

         - 原理

           ![image-20191203164312478](D:\study\activiti\images\image-20191203164312478.png)

       - 监听器的配置方式

         - 配置Listener

           - eventListeners：监听所有事件派发通知

           - typedEventListeners:监听指定事件类型的通知

           - activiti:eventListener：只监听特定流程定义的事件
        ![image-20191203170242316](D:\study\activiti\images\image-20191203170242316.png)
     
           - 相关API

             ![image-20191203170412107](D:\study\activiti\images\image-20191203170412107.png)

         - 

       - Activiti的事件监听

     - 命令拦截器的配置

       - 命令模式和责任链模式
     
         - 命令模式
     
           ![image-20191204162415520](D:\study\activiti\images\image-20191204162415520.png)
     
           ![image-20191204162455970](D:\study\activiti\images\image-20191204162455970.png)
     
         - 责任链模式
     
           ![image-20191204162555057](D:\study\activiti\images\image-20191204162555057.png)
     
       - 拦截器配置方式
     
         - 配置Interceptor
     
           ![image-20191204162747386](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20191204162747386.png)
     
         - 
     
       - Activiti的拦截器
     
     - 作业执行器Job Executor
     
       - 作业执行器的配置
     
         - 相关配置
     
           ![image-20191204165145399](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20191204165145399.png)
     
           
     
       - 配置自定义线程池
     
         - 自定义线程池ExecutorService
     
           ![image-20191204165243945](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20191204165243945.png)
     
         - 如何配置
     
           ![image-20191204165547105](D:\study\activiti\images\image-20191204165547105.png)
     
       - 流程定义定时启动流程
     
         - 定时开始事件
     
           ![image-20191204165721649](D:\study\activiti\images\image-20191204165721649.png)
     
     - Actviti与Spring集成
     
       - 集成Spring配置
     
         - 相关配置
           - 添加pom依赖activiti-spring
           - 基于Spring的默认配置activiti-context.xml
           - Activiti核心服务注入Spring容器
     
       - 基于Spring对Activiti管理
     
         - 功能特征
           - 集成Spring事务管理器
           - 定义文件表达式中使用Spring bean
           - 自动部署资源文件
     
       - 基于Spring的流程单元测试
     
         - 单元测试
           - 添加pom依赖spring-test
           - 辅助测试Rule：ActivitiRule
           - 辅助测试TestCase：SpringActivitiTestCase
     
   
2. 流程引擎及服务

   ![image-20191205145032122](D:\study\activiti\images\image-20191205145032122.png)

   - 流程存储服务

     - RepositoryService

       - 管理流程定义文件xml及静态资源的服务
       - 对特定流程的暂停和激活
       - 流程定义启动权限管理
       - 部署文件构造器DeploymentBuilder
       - 部署文件查询器DeploymentQuery
       - 流程定义文件查询对象ProcessDefinitionQuery
       - 流程部署文件对象Deployment
       - 流程定义文件对象ProcessDefinition
       - 流程定义的Java格式BpmnModel

     - RuntimeService

       - 启动流程及对流程数据的控制

       - 流程实例（ProcessInstance）与执行流（Execution）查询

       - 触发流程操作、接收消息和信号

       - 启动流程及变量管理

         - 启动流程的常用方式（id，key，message）
         - 启动流程可选参数（businessKey，variables，tenantId）
         - 变量（variables）的设置和获取 

       - 流程实例与执行流

         - 流程实例表示一次工作流业务的数据实体
         - 执行流标识流程实例中的具体的执行路径
         - 流程实例接口继承与执行流

       - 流程触发

         - 使用trigger触发ReceiveTask

         - 触发信号捕获事件signalEventReceived

           ![image-20191205170711683](D:\study\activiti\images\image-20191205170711683.png)

         - 触发消息捕获事件messageEventReceived

           ​	![image-20191205171444734](D:\study\activiti\images\image-20191205171444734.png)

     - TaskService

       - 对用户任务(UserTask)管理和流程的控制
         - Task对象的创建、删除
         - 查询Task，并驱动Task节点完成执行
         - Task相关参数变量（variable）设置
       - 设置用户任务（UserTask）的权限信息（拥有者、候选人、办理人）
         - 候选用户（candidateUser）和候选组（candidateGroup）
         - 指定拥有人（Owner）和办理人（Assignee）
         - 通过claim设置办理人
       - 针对用户任务添加任务附件、任务评论和事件记录
         - 任务附件（Attachment）创建与查询
         - 任务评论（Comment）创建与查询
         - 事件记录（Event）创建与查询
       
     - IdentityService

       - 管理用户（User）

       - 管理用户组（Group）

       - 用户与用户组的关系（Membership）

         ![image-20191209165838709](D:\study\activiti\images\image-20191209165838709.png)

     - FormService

       - 解析流程定义中表单项的配置
       - 提交表单的方式驱动用户节点流转
       - 获取自定义外部表单key

     - HistoryService

       - 管理流程实例结束后的历史数据 

         ![image-20191209173227034](D:\study\activiti\images\image-20191209173227034.png)

       - 构建历史数据的查询对象

         - create[历史数据实体]Query
         - createNative[历史数据实体]Query
         - createProcessInstanceHistoryLogQuery

       - 根据流程实例ID删除流程历史数据

         - deleteHistoricProcessInstance
         - deleteHistoricTaskInstance

     - 管理服务ManagementService

       - Job任务管理

         - Job任务查询

           ![image-20191210162012269](D:\study\activiti\images\image-20191210162012269.png)

       - 数据库相关通用操作

         - 查询表结构元数据（TableMetaData）
         - 通用表查询（TablePageQuery）
         - 执行自定义的Sql查询（executeCustomSql）

       - 

     - 动态流程定义服务DynamicBpmnService

     - 异常策略

       - ActivitiException

         ![image-20191210171205796](D:\study\activiti\images\image-20191210171205796.png)

     - 

       

   - 

3. 数据库模型设计

   ![image-20191211142616233](D:\study\activiti\images\image-20191211142616233.png)

   - Mysql建表语句

     - 核心引擎activiti.mysql.create.engine.sql
     - 历史数据activiti.mysql.create.history.sql
     - 身份信息activiti.mysql.create.identity.sql

   - Mysql删除表语句

     - 核心引擎activiti.mysql.drop.engine.sql
     - 历史数据activiti.mysql.drop.history.sql
     - 身份信息activiti.mysql.drop.identity.sql

   - 通用数据库

     ![image-20191211143230646](D:\study\activiti\images\image-20191211143230646.png)

     - ![image-20191211143308616](D:\study\activiti\images\image-20191211143308616.png)
     - ![image-20191211143322984](D:\study\activiti\images\image-20191211143322984.png)

   - 流程定义存储表

     ![image-20191211144334386](D:\study\activiti\images\image-20191211144334386.png)

     - 流程部署表

       ![image-20191211144354122](D:\study\activiti\images\image-20191211144354122.png)

     - 流程定义表

       ![image-20191211144420128](D:\study\activiti\images\image-20191211144420128.png)

   - 身份数据表

     ![image-20191211145030130](D:\study\activiti\images\image-20191211145030130.png)

     - 用户信息表

       ![image-20191211145055018](D:\study\activiti\images\image-20191211145055018.png)

     - 用户扩展信息表

       ![image-20191211145111617](D:\study\activiti\images\image-20191211145111617.png)

     - 用户组表

       ![image-20191211145149913](D:\study\activiti\images\image-20191211145149913.png)

     - 用户组关系表

       ![image-20191211145210298](D:\study\activiti\images\image-20191211145210298.png)

   - 运行时流程数据表

     ![image-20191211145711809](D:\study\activiti\images\image-20191211145711809.png)

     - 流程实例执行表

        ![image-20191211145823897](D:\study\activiti\images\image-20191211145823897.png)

       ![image-20191211145859395](D:\study\activiti\images\image-20191211145859395.png)

     - 用户任务表

       ![image-20191211145937091](D:\study\activiti\images\image-20191211145937091.png)

       ![image-20191211150006707](D:\study\activiti\images\image-20191211150006707.png)

     - 变量信息表

       ![image-20191211150033026](D:\study\activiti\images\image-20191211150033026.png)

     - 参与者信息表

       ![image-20191211150120649](D:\study\activiti\images\image-20191211150120649.png)

     - 事件订阅信息表

       ![image-20191211150144466](D:\study\activiti\images\image-20191211150144466.png)

     - 作业信息表

       ![image-20191211150200490](D:\study\activiti\images\image-20191211150200490.png)

       ![image-20191211150256027](D:\study\activiti\images\image-20191211150256027.png)

     - 

   - 历史流程数据表

     ![image-20191211154745754](D:\study\activiti\images\image-20191211154745754.png)

     - 历史流程实例表

       ![image-20191211154951172](D:\study\activiti\images\image-20191211154951172.png)

       ![image-20191211155125075](D:\study\activiti\images\image-20191211155125075.png)

     - 事件日志表

       ![image-20191211155200828](D:\study\activiti\images\image-20191211155200828.png)

       ![image-20191211155319332](D:\study\activiti\images\image-20191211155319332.png)

       

4. BPMN2.0概述

   - BPMN2.0(Business Process Model and Notation)

     - 一套业务流程模型与符号建模标准

     - 精准的执行语义来描述元素的操作

     - 以XML为载体，以符号可视化业务

       ![image-20191211160902048](D:\study\activiti\images\image-20191211160902048.png)

   - BPMN2.0元素

     ![image-20191211160929532](D:\study\activiti\images\image-20191211160929532.png)

     ![image-20191211161004101](D:\study\activiti\images\image-20191211161004101.png)

     - 流对象（Flow Object）

       - 活动（Activities）【User Task、Service Task】

       - 事件（Events）【Start Event、End Event】

         - 分类方式

           - 位置分类

             - 开始事件
             - 中间事件/边界事件
             - 结束事件

           - 特性分类

             - 捕获事件（Catching）
             - 抛出事件（Throwing）

           - 事件定义分类

             - 定时事件

               - 指定时间（timeDate）

               - 指定持续时间（timeDuration）

               - 周期执行（timeCycle）

                 ![image-20191211162202494](D:\study\activiti\images\image-20191211162202494.png)

               - 定时事件举例

                 - 定时开始事件

                   ![image-20191211162347182](D:\study\activiti\images\image-20191211162347182.png)

                 - 定时边界事件

                   ![image-20191211162421708](D:\study\activiti\images\image-20191211162421708.png)

                 - 

             - 错误事件

               - 错误事件定义

                 ![image-20191218094842899](D:\study\activiti\images\image-20191218094842899.png)

               - 错误边界事件
             
                 ![image-20191218095831043](D:\study\activiti\images\image-20191218095831043.png)
             
             - 信号事件
             
               - 信号开始事件
             
                 ![image-20191218100727725](D:\study\activiti\images\image-20191218100727725.png)
             
               - 信号中间事件
             
                 ![image-20191218100821399](D:\study\activiti\images\image-20191218100821399.png)
             
             - 消息事件
             
               - 消息事件定义
             
                 ![image-20191218100910849](D:\study\activiti\images\image-20191218100910849.png)
             
               - 
             
             

       - 顺序流和网关（Gateways）【Exclusive Gateway...】

         ![image-20191211161319155](D:\study\activiti\images\image-20191211161319155.png)
         
         - 顺序流
         
           - 条件顺序流
         
             ![image-20191218111946003](D:\study\activiti\images\image-20191218111946003.png)
         
           - 默认顺序流
         
             ![image-20191218111958027](D:\study\activiti\images\image-20191218111958027.png)
         
           - 
         
         - 网关
         
           - 单一网关（Exclusive Gateway）
         
             - 单一网关顺序流实例
         
               ![image-20191218112247611](D:\study\activiti\images\image-20191218112247611.png)
         
             - 单一网关顺序流定义
         
               ![image-20191218112354304](D:\study\activiti\images\image-20191218112354304.png)
         
           - 并行网关(Parallel Gateway)
         
             ![image-20191218112704542](D:\study\activiti\images\image-20191218112704542.png)
         
             - 并行网关流程定义
         
               ![image-20191218112732699](D:\study\activiti\images\image-20191218112732699.png)
         
             - 
         
           - 包容性网关(Inclusive Gateway)
         
             ![image-20191218142212001](D:\study\activiti\images\image-20191218142212001.png)
         
             - 
         
           - 基于事件网关
         
         

     - 流程任务

       ![image-20191218101559313](D:\study\activiti\images\image-20191218101559313.png)
       
       - 核心流程任务
       
         - 用户任务（User Task）
       
           ![image-20191218101714933](D:\study\activiti\images\image-20191218101714933.png)
       
           ![image-20191218101741001](D:\study\activiti\images\image-20191218101741001.png)
       
           - 用户任务候选人/组配置
             ![image-20191218101830218](D:\study\activiti\images\image-20191218101830218.png)
       
           - 用户任务代理人配置
       
             ![image-20191218101930282](D:\study\activiti\images\image-20191218101930282.png)
       
           - 通过任务监听器自定义配置
       
             ![image-20191218102005720](D:\study\activiti\images\image-20191218102005720.png)
       
             ![image-20191218102031936](D:\study\activiti\images\image-20191218102031936.png)
       
           - 
       
         - 脚本任务（Script Task）
       
           - JUEL（默认）- el表达式
       
           - Groovy脚本（依赖groovy-all.jar）
       
             ![image-20191218103804918](D:\study\activiti\images\image-20191218103804918.png)
       
           - Javascript脚本
       
             ![image-20191218103616844](D:\study\activiti\images\image-20191218103616844.png)
       
           - 脚本任务设置返回值
       
             ![image-20191218103905768](D:\study\activiti\images\image-20191218103905768.png)
       
           - 
       
         - 服务任务（Java Service Task）
       
           ![image-20191218105117962](D:\study\activiti\images\image-20191218105117962.png)
       
           - 执行Java程序的方法
       
             - 执行实现JavaDelegate或ActivityBehavior的类
       
               ![image-20191218105324363](D:\study\activiti\images\image-20191218105324363.png)
       
               - JavaDelegate注入属性
       
                 ![image-20191218110130599](D:\study\activiti\images\image-20191218110130599.png)
       
             - 执行一个JavaDelegate对象表达式，通常Spring配置Bean
       
               ![image-20191218111017450](D:\study\activiti\images\image-20191218111017450.png)
       
             - 执行调用方法表达式和值表达式
       
               ![image-20191218111552902](D:\study\activiti\images\image-20191218111552902.png)
       
             - JavaDelegate抛出错误事件
       
               ![image-20191218111631132](D:\study\activiti\images\image-20191218111631132.png)
       
             - 
         
       - 子流程
       
          <img src="D:\study\activiti\images\image-20191218142428969.png" alt="image-20191218142428969" style="zoom:80%;" />
       
         - 子流程（Sub-Process）
       
           ![image-20191218142724652](D:\study\activiti\images\image-20191218142724652.png)
       
           
       
         - 事件子流程（Event Sub-Process）
       
           ![image-20191218165539211](D:\study\activiti\images\image-20191218165539211.png)
       
         - 事务子流程（Transaction Sub-Process）
       
           ![image-20191218165845032](D:\study\activiti\images\image-20191218165845032.png)
       
         - 调用式子流程（Call Activity）
       
           ![image-20191218170257792](D:\study\activiti\images\image-20191218170257792.png)

   - 

5. Activiti源码初探

   - git获取源码

     - 登陆http://github.com,将Activiti的源码fork到本地代码库

       - 我们可以将自己修改的代码pull到官网库
       - 也可以将官网修改的代码push到本地，然后merge一下
       - 登陆https://git-scm.com/downloads网站下载git bash
       - 然后配置[ssh登陆](https://blog.csdn.net/sxg0205/article/details/81412921)

     - 获取源码指令

       ![image-20191220161522557](D:\study\activiti\images\image-20191220161522557.png)

     - 

6. 