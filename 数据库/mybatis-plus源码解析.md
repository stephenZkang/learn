### mybatis-plus源码解析

#### 源码环境部署

1. gitee上下载mybatis-plus源码

   ```shell
   git clone https://gitee.com/qiaokang666/mybatis-plus.git
   ```

2. 由于mybatis-plus用的是gradle做的构建

   1. 下载gradle5.6.4（注意6.0会失败），地址：https://gradle.org/releases/

   2. 解压到D:\software\gradle-5.6.4

   3. D盘建立.gradle目录

      ```shell
      mkdir .gradle
      ```

   4. 环境变量配置gradle

   5. 将init.gradle放到D:\software\gradle-5.6.4\init.d目录,配置了阿里镜像

      ```groovy
      allprojects{
          repositories {
              def ALIYUN_REPOSITORY_URL = 'https://maven.aliyun.com/repository/public/'
              def ALIYUN_JCENTER_URL = 'https://maven.aliyun.com/repository/jcenter/'
              def ALIYUN_GOOGLE_URL = 'https://maven.aliyun.com/repository/google/'
              def ALIYUN_GRADLE_PLUGIN_URL = 'https://maven.aliyun.com/repository/gradle-plugin/'
              all { ArtifactRepository repo ->
                  if(repo instanceof MavenArtifactRepository){
                      def url = repo.url.toString()
                      if (url.startsWith('https://repo1.maven.org/maven2/')) {
                          project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                          remove repo
                      }
                      if (url.startsWith('https://jcenter.bintray.com/')) {
                          project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                          remove repo
                      }
                      if (url.startsWith('https://dl.google.com/dl/android/maven2/')) {
                          project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_GOOGLE_URL."
                          remove repo
                      }
                      if (url.startsWith('https://plugins.gradle.org/m2/')) {
                          project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_GRADLE_PLUGIN_URL."
                          remove repo
                      }
                  }
              }
              maven { url ALIYUN_REPOSITORY_URL }
              maven { url ALIYUN_JCENTER_URL }
              maven { url ALIYUN_GOOGLE_URL }
              maven { url ALIYUN_GRADLE_PLUGIN_URL }
          }
      }
      ```

   6. 

3. 使用IDEA打开mybatis-plus项目

   IDEA配置gradle，File - Settings - Build,Execution,Deployment - Gradle

   ![image-20200512155621140](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512155621140.png)

4. 环境配置完成，如下图

   ![image-20200512155717438](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512155717438.png)

#### 源码分析

##### 工程解析

- mybatis-plus

  测试类

- mybatis-plus-annotation

  定义了mybatis-plus的注解

- mybatis-plus-boot-starter

  mybatis-plus对spring boot的支持

- mybatis-plus-core

  mybatis-plus的核心源码

- mybatis-plus-dts

  

- mybatis-plus-extension

  mybatis-plus的扩展，插件的支持，如分页、性能解析

- mybatis-plus-generator

  mybatis-plus的根据库自动生成代码

##### **插件架构**

mybatis架构图

![image-20200515100502853](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200515100502853.png)

![image-20200515103331522](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200515103331522.png)

###### mybatis流程图

![image-20200515100101979](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200515100101979.png)

###### mybatis缓存机制

![image-20200515100300117](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200515100300117.png)



##### 插件支持

###### 分页插件（扩展其他厂家数据库支持）

- 支持的数据库类型有：DB2、达蒙、H2、HSQL、Kingbase、MariaDB、Mysql、Oracle、Phoenix、Postgre、SQLite、SQLServer、XuGu（虚谷）

- **PaginationInterceptor**实现了Mybatis的拦截器**Interceptor**，继承了处理器**AbstractSqlParserHandler**

  ![image-20200512162749464](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512162749464.png)

  ![image-20200512163721622](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512163721622.png)

  **【1】红色部分确定数据库类型。通过手动设置dbType或者连接数据库url协议来判断**

  ![image-20200512163825624](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512163825624.png)

  **【2】通过方言处理分页SQL**

  ![image-20200512164033380](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512164033380.png)

  H2数据库的方言

  **【3】根据数据库类型获取方言，方言要与数据库类型关联**

  ![image-20200512164844180](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512164844180.png)

  ![image-20200512164819214](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200512164819214.png)

  结论，**扩展其他厂家的数据库**，如下：

  1. **在JdbcUtils的getDbType中增加url协议解析**
  2. **实现IDialect接口对分页做处理**
  3. **在DialectRegistry类中填上对应的厂家数据库类型和方言的关联**

  两种方式扩展：

  1. 修改源代码构建mybatis-plus的jar依赖（麻烦）
  2. 覆盖**JdbcUtils**和**DialectRegistry**，两个类，新增方言类**（推荐）**

- 





