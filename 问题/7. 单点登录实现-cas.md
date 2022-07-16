## 一、部署cas服务端

### 1、下载cas服务端

​	下载地址：https://github.com/apereo/cas-overlay-template/tree/5.2，cas server版本为5.26

![image-20200310083640574](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310083640574.png)

### 2、构建cas的war包

```shell
#解压下载的cas-overlay-template-5.2.zip
cd cas-overlay-template-5.2
#查看build脚本命令
build.cmd help
#构建war包
build.cmd package
```



![image-20200310084139328](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310084139328.png)	

解压cas.war到cas_war目录

### 3、IDEA中导入cas服务端

- IDEA 新建spring boot工程

  ![image-20200310084557647](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310084557647.png)

- File -> Project Structure -> ADD

  ![image-20200310084753290](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310084753290.png)

  ![image-20200310084855530](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310084855530.png)

  将D:\work_idea\资料\cas-overlay-template-5.2\target\cas_war\WEB-INF\lib路径引入

  ![image-20200310085044458](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310085044458.png)

- 配置pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.2.5.RELEASE</version>
          <relativePath/> <!-- lookup parent from repository -->
      </parent>
      <groupId>com.superred</groupId>
      <artifactId>cas</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <packaging>war</packaging>
      <name>cas</name>
      <description>cas project for Spring Boot</description>
  
      <properties>
          <cas.version>5.2.3</cas.version>
          <springboot.version>2.2.5.RELEASE</springboot.version>
          <!-- app.server could be -jetty, -undertow, -tomcat, or blank if you plan to provide appserver -->
          <app.server>-tomcat</app.server>
  
          <mainClassName>org.springframework.boot.loader.WarLauncher</mainClassName>
          <isExecutable>false</isExecutable>
          <manifestFileToUse>${project.build.directory}/war/work/org.apereo.cas/cas-server-webapp${app.server}/META-INF/MANIFEST.MF</manifestFileToUse>
  
          <maven.compiler.source>1.8</maven.compiler.source>
          <maven.compiler.target>1.8</maven.compiler.target>
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      </properties>
  
      <repositories>
          <repository>
              <id>sonatype-releases</id>
              <url>http://oss.sonatype.org/content/repositories/releases/</url>
              <snapshots>
                  <enabled>false</enabled>
              </snapshots>
              <releases>
                  <enabled>true</enabled>
              </releases>
          </repository>
          <repository>
              <id>sonatype-snapshots</id>
              <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
              <snapshots>
                  <enabled>true</enabled>
              </snapshots>
              <releases>
                  <enabled>false</enabled>
              </releases>
          </repository>
          <repository>
              <id>shibboleth-releases</id>
              <url>https://build.shibboleth.net/nexus/content/repositories/releases</url>
          </repository>
      </repositories>
  
      <dependencies>
          <dependency>
              <groupId>org.apereo.cas</groupId>
              <artifactId>cas-server-webapp${app.server}</artifactId>
              <version>${cas.version}</version>
              <type>war</type>
              <scope>runtime</scope>
          </dependency>
          <!-- 自己需要的jar包，我这里用到了查库验证身份，所以引入了mysql -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.44</version>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-tomcat</artifactId>
              <scope>provided</scope>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
              <exclusions>
                  <exclusion>
                      <groupId>org.junit.vintage</groupId>
                      <artifactId>junit-vintage-engine</artifactId>
                  </exclusion>
              </exclusions>
          </dependency>
      </dependencies>
  
      <build>
          <plugins>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <version>${springboot.version}</version>
                  <configuration>
                      <mainClass>${mainClassName}</mainClass>
                      <addResources>true</addResources>
                      <executable>${isExecutable}</executable>
                      <layout>WAR</layout>
                  </configuration>
                  <executions>
                      <execution>
                          <goals>
                              <goal>repackage</goal>
                          </goals>
                      </execution>
                  </executions>
              </plugin>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-war-plugin</artifactId>
                  <version>2.6</version>
                  <configuration>
                      <warName>cas</warName>
                      <failOnMissingWebXml>false</failOnMissingWebXml>
                      <recompressZippedFiles>false</recompressZippedFiles>
                      <archive>
                          <compress>false</compress>
                          <manifestFile>${manifestFileToUse}</manifestFile>
                      </archive>
                      <overlays>
                          <overlay>
                              <groupId>org.apereo.cas</groupId>
                              <artifactId>cas-server-webapp${app.server}</artifactId>
                          </overlay>
                      </overlays>
                  </configuration>
              </plugin>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <version>3.3</version>
              </plugin>
          </plugins>
          <finalName>cas</finalName>
      </build>
  
  </project>
  
  ```

  

- 复制文件

  [META-INF](cas-overlay-template-5.2\target\cas_war下的)目录复制到resources

  [application.properties](cas-overlay-template-5.2\target\cas_war\WEB-INF\classes)目录复制到resources

  [services](cas-overlay-template-5.2\target\cas_war\WEB-INF\classes\services)目录复制到resources

- 配置tomcat

  Run -> Edit Configurations

  <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310090305533.png" alt="image-20200310090305533" style="zoom:67%;" />

  <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310090425825.png" alt="image-20200310090425825" style="zoom:80%;" />

  添加cas服务

  <img src="https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310090520401.png" alt="image-20200310090520401" style="zoom:50%;" />

- 启动名为cas_server的tomcat

  **出现下面异常**

  ![image-20200310090713067](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310090713067.png)

  pom.xml中注释[spring-boot-starter-web]、[spring-boot-starter-tomcat]、[spring-boot-starter-test]三个依赖，同时删除代码，重新启动

  ![image-20200310091219521](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310091219521.png)

  **启动成功**

  登录，casuser::Mellon

  ![image-20200310091356649](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310091356649.png)

### 4、增量开发

**顺手贴上CAS 5.2.X官方文档：**https://apereo.github.io/cas/5.2.x/index.html

**hugeo的CAS系列：**https://blog.csdn.net/u010588262/article/category/7548325

CAS框架本身的功能是用来做登录验证的，其中用到了spring webflow，可以对Cas验证流程了解一下

![image-20200310094030477](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310094030477.png)

cas中没有注册、修改密码等，我们需要在cas_server上增加自己的功能，maver overlay不就是用来做这个的吗，大致思路是，把CAS自带的登录页面修改掉，上面增加注册，修改密码的按钮，指向咱们自己写的controller就行了。

**重要提醒：**

​	把CAS本身的lib全部添加到开发项目中，maven里不要有spring boot相关的依赖了，因为CAS的lib里就有spring boot核心的包，已经够我们开发了，CAS的lib里没有的包我们再添加依赖。否则打包时开放项目又打进去一份springboot的包，启动时就会报bean重复的问题（在两边版本不一致的情况下），所以保险起见就是pom中不要有springboot的依赖。

- 基础工作

  自定义controller后发现404错误，因为默认cas是扫描`org.apereo.cas.web`下寻找component，service，controller，configuration等组件的，所以在咱们自己的包下面使用这些注解spring是扫描不到的

  1. 增加配置类

     ```java
     package com.superred.cas;
     
     import org.springframework.context.annotation.ComponentScan;
     import org.springframework.context.annotation.Configuration;
     
     @Configuration
     @ComponentScan("com.superred.cas")
     public class SpringConfig {
     
     }
     
     ```

  2. 将此配置类配置到spring.factories中，否则spring不会管它

     resources下增加目录META-INF，下面新建配置文件spring.factories文件

     ```properties
     org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
       com.superred.cas.SpringConfig
     ```

  3. 写个简单的Controller测试一下

     ```java
     package com.superred.cas;
     
     import org.springframework.web.bind.annotation.GetMapping;
     import org.springframework.web.bind.annotation.RestController;
     
     @RestController
     public class RegController {
     
         @GetMapping("/test")
         public String test(){
             return "Hello World";
         }
     }
     
     ```

  4. 重启cas_server，访问：http://localhost:8080/cas/test

     ![image-20200310100112530](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310100112530.png)

- 自定义登录页面

  官方文档：https://apereo.github.io/cas/5.2.x/installation/User-Interface-Customization-Themes.html

  简单来说就是每个要使用单点登录的客户端都要在服务端有个配置文件：

  ![è¿éåå¾çæè¿°](https://raw.githubusercontent.com/stephenZkang/learn/master/img/70)

  这些文件固定在service文件夹下，文件生效要在application.properties中添加：

  ```properties
  #开启识别json文件，默认false
  cas.serviceRegistry.initFromJson=true
  ```

  文件各字段含义：
  1. @class固定的，没研究含义
  2. serviceId通过正则表达式匹配客户端过来的url
  3. evaluationOrder，如果多个文件的正则都匹配了咋办，哪个Order小用哪个
  4. name desc id不说了
  5. 细心的小伙伴发现了这里比原来的博客里多了一个theme，就是控制这个客户端过来的登录页面样式了，可以支持不同客户端不同登录页面，很人性化。

  **自定义登录页面**

  在application.properties中配置默认默认主题

  ```properties
  # 默认主题
  cas.theme.defaultThemeName=hugeo
  ```

  下面开始创建咱们自己的登录页面吧，涉及到以下几个文件：

  ![image-20200310100444824](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310100444824.png)

  因为我们在json文件中配置的主题名称是hugeo，所以在templates下面创建同名hugeo文件夹，文件夹里面是casLoginView.html，这个名称也是固定的：

  ```html
  <!DOCTYPE html>
  <html>
  <head>
      <meta charset="UTF-8"/>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      <title th:text="${#themes.code('demo.pageTitle')}"></title>
      <link rel="stylesheet" th:href="@{${#themes.code('hugeo.css.file')}}"/>
      <script th:src="@{${#themes.code('hugeo.js.file')}}"></script>
  </head>
  
  <body>
  <h1 th:text="${#themes.code('demo.pageTitle')}"></h1>
  <div>
      <form method="post" th:object="${credential}">
          <div th:if="${#fields.hasErrors('*')}">
              <span th:each="err : ${#fields.errors('*')}" th:utext="${err}"/>
          </div>
          <h2 th:utext="#{screen.welcome.instructions}"></h2>
  
          <section class="row">
              <label for="username" th:utext="#{screen.welcome.label.netid}"/>
              <div th:unless="${openIdLocalId}">
                  <input class="required"
                         id="username"
                         size="25"
                         tabindex="1"
                         type="text"
                         th:disabled="${guaEnabled}"
                         th:field="*{username}"
                         th:accesskey="#{screen.welcome.label.netid.accesskey}"
                         autocomplete="off"/>
              </div>
          </section>
  
          <section class="row">
              <label for="password" th:utext="#{screen.welcome.label.password}"/>
              <div>
                  <input class="required"
                         type="password"
                         id="password"
                         size="25"
                         tabindex="2"
                         th:accesskey="#{screen.welcome.label.password.accesskey}"
                         th:field="*{password}"
                         autocomplete="off"/>
              </div>
          </section>
  
          <section>
              <input type="hidden" name="execution" th:value="${flowExecutionKey}"/>
              <input type="hidden" name="_eventId" value="submit"/>
              <input type="hidden" name="geolocation"/>
              <input class="btn btn-submit btn-block"
                     name="submit"
                     accesskey="l"
                     th:value="#{screen.welcome.button.login}"
                     tabindex="6"
                     type="submit"/>
              <a th:href="@{/reg}">点我注册</a>
          </section>
      </form>
  </div>
  </body>
  </html>
  ```

  里面以`${#themes.code('hugeo.js.file')}`形式获取的参数是从主题同名文件hugeo.properties中获取的：

  ```properties
  hugeo.css.file=/themes/hugeo/css/cas.css
  hugeo.js.file=/themes/hugeo/js/cas.js
  demo.pageTitle=this is it
  ```

  js，css文件自己创建就行了，可以在html里面直接用，也可以定义在hugeo.properties中然后在html里使用：

  ```html
  <link rel="stylesheet" th:href="@{${#themes.code('hugeo.css.file')}}"/>
  <script th:src="@{${#themes.code('hugeo.js.file')}}"></script>
  ```

  

- 增加验证码

  重点关注之前提到的webflow了

  看到login-webflow.xml中有这样的内容

  ![image-20200310102509942](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310102509942.png)

  1. login-webflow.xml的位置

  2. 简单理解为对应的登录页面，咱们自定义的登录页面就叫casLoginView.xml，还记得吧

  3. 3和4结合起来应该可以敏感地感觉到是把页面的username和password注入到credential这个model里面吧

  4. 那credential这个model到底是哪个类呢，我在jar包里找了与cas webflow相关的类，找到了一个关键的配置接口CasWeflowConfigurer，它的继承类如下：
      ![image-20200310102844846](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310102844846.png)

    5. 重新credential，继承`UsernamePasswordCredential`，加上属性capcha

       ```java
       package com.superred.cas;
       
       import org.apache.commons.lang.builder.HashCodeBuilder;
       import org.apereo.cas.authentication.UsernamePasswordCredential;
       
       import javax.validation.constraints.Size;
       
       public class UsernamePasswordCaptchaCredential extends UsernamePasswordCredential {
       
           @Size(min = 5,max = 5, message = "require capcha")
           private String capcha;
           public String getCapcha() {
               return capcha;
           }
       
           public UsernamePasswordCaptchaCredential setCapcha(String capcha) {
               this.capcha = capcha;
               return this;
           }
       
           @Override
           public int hashCode() {
               return new HashCodeBuilder()
                       .appendSuper(super.hashCode())
                       .append(this.capcha)
                       .toHashCode();
           }
       
       }
       ```

       

    2. 新建`HugeoWebflowConfigurer`，继承DefaultWebflowConfigurer 

       ```java
       package com.superred.cas;
       
       import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;
       import org.apereo.cas.configuration.CasConfigurationProperties;
       import org.apereo.cas.web.flow.configurer.DefaultWebflowConfigurer;
       import org.springframework.context.ApplicationContext;
       import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
       import org.springframework.webflow.engine.Flow;
       import org.springframework.webflow.engine.ViewState;
       import org.springframework.webflow.engine.builder.BinderConfiguration;
       import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
       
       
       
       public class HugeoWebflowConfigurer extends DefaultWebflowConfigurer {
       
           public HugeoWebflowConfigurer(FlowBuilderServices flowBuilderServices, FlowDefinitionRegistry flowDefinitionRegistry, ApplicationContext applicationContext, CasConfigurationProperties casProperties) {
               super(flowBuilderServices, flowDefinitionRegistry, applicationContext, casProperties);
           }
       
           @Override
           protected void createRememberMeAuthnWebflowConfig(Flow flow) {
               if (this.casProperties.getTicket().getTgt().getRememberMe().isEnabled()) {
                   this.createFlowVariable(flow, "credential", RememberMeUsernamePasswordCredential.class);
                   ViewState state = (ViewState) this.getState(flow, "viewLoginForm", ViewState.class);
                   BinderConfiguration cfg = this.getViewStateBinderConfiguration(state);
                   cfg.addBinding(new BinderConfiguration.Binding("rememberMe", (String) null, false));
               } else {
                   this.createFlowVariable(flow, "credential", UsernamePasswordCaptchaCredential.class);
                   ViewState state = (ViewState) this.getState(flow, "viewLoginForm", ViewState.class);
                   BinderConfiguration cfg = this.getViewStateBinderConfiguration(state);
                   cfg.addBinding(new BinderConfiguration.Binding("capcha", (String) null, true));
               }
       
           }
       
       }
           
       ```

       

       主要修改了这里，把原来的UsernamePasswordCredential换成了我们自己的UsernamePasswordCaptchaCredential，并且加上cpacha的bind，也可以在login-webflow.xml里面加，跟username，password类似的

       

    3. 注册`HugeoWebflowConfigurer`

       ```java
       package com.superred.cas;
       
       import org.apereo.cas.configuration.CasConfigurationProperties;
       import org.apereo.cas.web.flow.CasWebflowConfigurer;
       import org.apereo.cas.web.flow.config.CasWebflowContextConfiguration;
       import org.springframework.beans.factory.annotation.Autowired;
       import org.springframework.beans.factory.annotation.Qualifier;
       import org.springframework.boot.autoconfigure.AutoConfigureBefore;
       import org.springframework.boot.context.properties.EnableConfigurationProperties;
       import org.springframework.context.ApplicationContext;
       import org.springframework.context.annotation.Bean;
       import org.springframework.context.annotation.Configuration;
       import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
       import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
       
       @Configuration("hugeoWebflowConfiguration")
       @EnableConfigurationProperties(CasConfigurationProperties.class)
       @AutoConfigureBefore(value = CasWebflowContextConfiguration.class)
       public class HugeoWebflowConfiguration {
           @Autowired
           @Qualifier("logoutFlowRegistry")
           private FlowDefinitionRegistry logoutFlowRegistry;
           @Autowired
           @Qualifier("loginFlowRegistry")
           private FlowDefinitionRegistry loginFlowRegistry;
           @Autowired
           private ApplicationContext applicationContext;
           @Autowired
           private CasConfigurationProperties casProperties;
       
           @Autowired
           @Qualifier("builder")
           private FlowBuilderServices builder;
       
           @Bean("defaultWebflowConfigurer")
           public CasWebflowConfigurer customWebflowConfigurer() {
               final HugeoWebflowConfigurer c = new HugeoWebflowConfigurer(builder, loginFlowRegistry, applicationContext, casProperties);
               c.setLogoutFlowDefinitionRegistry(logoutFlowRegistry);
               c.initialize();
               return c;
           }
       }
       ```

       ![image-20200310103814488](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310103814488.png)

  

    4. 接管身份验证

       ```java
       package com.superred.cas;
       
       
       import org.apereo.cas.authentication.Credential;
       import org.apereo.cas.authentication.HandlerResult;
       import org.apereo.cas.authentication.PreventedException;
       import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
       import org.apereo.cas.authentication.principal.PrincipalFactory;
       import org.apereo.cas.services.ServicesManager;
       import org.springframework.jdbc.core.JdbcTemplate;
       import org.springframework.jdbc.datasource.DriverManagerDataSource;
       import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
       import org.springframework.web.context.request.RequestContextHolder;
       import org.springframework.web.context.request.ServletRequestAttributes;
       
       import javax.security.auth.login.FailedLoginException;
       import java.security.GeneralSecurityException;
       import java.util.HashMap;
       import java.util.Map;
       
       public class Login extends AbstractPreAndPostProcessingAuthenticationHandler {
       
           public Login(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
               super(name, servicesManager, principalFactory, order);
           }
       
           @Override
           protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
               UsernamePasswordCaptchaCredential mycredential1 = (UsernamePasswordCaptchaCredential) credential;
       
               String capcha = mycredential1.getCapcha();
               /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
               String right = attributes.getRequest().getSession().getAttribute("capcha").toString();
               if(!capcha.equals(right)){
                   throw new FailedLoginException("验证码错误");
               }*/
       
               DriverManagerDataSource d=new DriverManagerDataSource();
               d.setDriverClassName("com.mysql.jdbc.Driver");
               d.setUrl("jdbc:mysql://127.0.0.1:3306/test");
               d.setUsername("root");
               d.setPassword("root");
               JdbcTemplate template=new JdbcTemplate();
               template.setDataSource(d);
       
       
       
       
               String username=mycredential1.getUsername();
               //查询数据库加密的的密码
               Map<String,Object> user = template.queryForMap("SELECT `password` FROM t_sec_user WHERE user_name = ?", mycredential1.getUsername());
       
               if(user==null){
                   throw new FailedLoginException("没有该用户");
               }
       
               //返回多属性（暂时不知道怎么用，没研究）
               Map<String, Object> map=new HashMap<>();
               map.put("email", "3105747142@qq.com");
       
               BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       
               if(encoder.matches(mycredential1.getPassword(),encoder.encode(user.get("password").toString()))){
                   return createHandlerResult(mycredential1, principalFactory.createPrincipal(username, map), null);
               }
               throw new FailedLoginException("Sorry, login attemp failed.");
           }
       
           @Override
           public boolean supports(Credential credential) {
               return credential instanceof UsernamePasswordCaptchaCredential;
           }
       }
       ```

       

    5. 界面增加验证码

       在咱们自定义登录页面的基础上，在casLoginView.html中添加：

       ```html
       <section class="row">
           <label for="capcha">验证码</label>
           <div>
               <input class="required"
                      id="capcha"
                      name="capcha"
                      size="25"
                      tabindex="3"
                      th:field="*{capcha}"
                      autocomplete="off"/>
               <img th:src="@{/capcha}" style="width: 160px;height:40px; ">
           </div>
       </section>
       ```

       

    6. 验证码类

       ```java
       package com.superred.cas;
       
       import org.springframework.stereotype.Controller;
       import org.springframework.web.bind.annotation.GetMapping;
       
       import javax.servlet.http.HttpServletRequest;
       import javax.servlet.http.HttpServletResponse;
       import java.io.IOException;
       
       @Controller
       public class CapController {
       
           @GetMapping("/capcha")
           public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
               IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
               try {
                   //设置长宽
                   VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
                   String code = verifyCode.getCode();
       
                   //将VerifyCode绑定session
                   request.getSession().setAttribute("capcha", code);
                   //设置响应头
                   response.setHeader("Pragma", "no-cache");
                   //设置响应头
                   response.setHeader("Cache-Control", "no-cache");
                   //在代理服务器端防止缓冲
                   response.setDateHeader("Expires", 0);
                   //设置响应内容类型
                   response.setContentType("image/jpeg");
                   response.getOutputStream().write(verifyCode.getImgBytes());
                   response.getOutputStream().flush();
               } catch (IOException e) {
       
               }
           }
       
       }
       ```


  ​    

    7. 

- 



## 二、部署cas客户端

### 1、下载cas客户端demo

- 下载样例

  地址：https://gitee.com/hhfa226/single_sign_on?_from=gitee_search

- IDEA导入demo

  ![image-20200310091735288](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310091735288.png)

- 修改配置application.yml,下面部分修改为自己的地址

  ```yaml
  cas:
    client-name: demo1Client
    server:
      url: http://localhost:8080/cas
    project:
      url: http://localhost:8086/demo1
  ```

  

- 启动客户端

  访问地址：http://localhost:8086/demo1

  ![image-20200310092336776](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310092336776.png)

  **出现上面现象，原因**：cas_server未配置相应的服务

  关掉cas_server

  **配置服务**

  在resources/services下配置对应的服务，新建文件Localhost-10000003.json

  修改json内容

  ```json
  {
    "@class" : "org.apereo.cas.services.RegexRegisteredService",
    "serviceId" : "^(http)://localhost:8086.*",
    "name" : "本地服务",
    "id" : 10000003,
    "description" : "这是本地服务",
    "evaluationOrder" : 1
  }
  ```

  修改配置文件,尾部增加一下内容

  ```properties
  cas.tgc.secure=false 
  cas.serviceRegistry.initFromJson=true
  cas.serviceRegistry.watcherEnabled=true
  cas.serviceRegistry.schedule.repeatInterval=120000
  cas.serviceRegistry.schedule.startDelay=15000
  cas.serviceRegistry.managementType=DEFAULT
  cas.serviceRegistry.json.location=classpath:/services
  cas.logout.followServiceRedirects=true
  ```

  再次访问：http://localhost:8086/demo1

  ![image-20200310093059915](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310093059915.png)

  跳转到登录页面，登录后看到demo的服务

  ![image-20200310093151366](https://raw.githubusercontent.com/stephenZkang/learn/master/img/image-20200310093151366.png)

  

- 