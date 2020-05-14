# Web安全漏洞深入分析及其安全编码

超全Web漏洞详解及其对应的安全编码规则，包括：SQL注入、XSS、CSRF、文件上传、路径遍历、越权、XML以及业务安全等，实例告诉你各个漏洞对应的编码规则。给你的代码加把安全锁！

文章目录

[一、Web安全基础](https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#%E4%B8%80web%E5%AE%89%E5%85%A8%E5%9F%BA%E7%A1%80)

[1.1 常见的Web安全漏洞](https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#11-%E5%B8%B8%E8%A7%81%E7%9A%84web%E5%AE%89%E5%85%A8%E6%BC%8F%E6%B4%9E)

[1.2 安全编码原则]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#12-%E5%AE%89%E5%85%A8%E7%BC%96%E7%A0%81%E5%8E%9F%E5%88%99](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#12-安全编码原则))

[1.3 数据验证]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#13-%E6%95%B0%E6%8D%AE%E9%AA%8C%E8%AF%81](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#13-数据验证))

[1.4 身份认证&会话管理]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#14-%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%E4%BC%9A%E8%AF%9D%E7%AE%A1%E7%90%86](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#14-身份认证会话管理))

[1.5 授权管理]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#15-%E6%8E%88%E6%9D%83%E7%AE%A1%E7%90%86](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#15-授权管理))

[1.6 存储安全]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#16-%E5%AD%98%E5%82%A8%E5%AE%89%E5%85%A8](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#16-存储安全))

[二、SQL注入及其安全编码]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#%E4%BA%8Csql%E6%B3%A8%E5%85%A5%E5%8F%8A%E5%85%B6%E5%AE%89%E5%85%A8%E7%BC%96%E7%A0%81](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#二sql注入及其安全编码))

[2.1 定义]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#21-%E5%AE%9A%E4%B9%89](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#21-定义))

[2.2 经典SQL注入代码示例]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#22-%E7%BB%8F%E5%85%B8sql%E6%B3%A8%E5%85%A5%E4%BB%A3%E7%A0%81%E7%A4%BA%E4%BE%8B](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#22-经典sql注入代码示例))

[2.3 文艺的SQL注入]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#23-%E6%96%87%E8%89%BA%E7%9A%84sql%E6%B3%A8%E5%85%A5](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#23-文艺的sql注入))

[2.4 SQL注入防护]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#24-sql%E6%B3%A8%E5%85%A5%E9%98%B2%E6%8A%A4](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#24-sql注入防护))

[2.5 MyBatis的SQL注入防护]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#25-mybatis%E7%9A%84sql%E6%B3%A8%E5%85%A5%E9%98%B2%E6%8A%A4](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#25-mybatis的sql注入防护))

[三、跨站脚本攻击及其安全编码]([[https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#%E4%B8%89%E8%B7%A8%E7%AB%99%E8%84%9A%E6%9C%AC%E6%94%BB%E5%87%BB%E5%8F%8A%E5%85%B6%E5%AE%89%E5%85%A8%E7%BC%96%E7%A0%81](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#三跨站脚本攻击及其安全编码)))

[3.1 定义]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#31-%E5%AE%9A%E4%B9%89](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#31-定义))

[3.2 XSS攻击模式]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#32-xss%E6%94%BB%E5%87%BB%E6%A8%A1%E5%BC%8F](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#32-xss攻击模式))

[3.3 XSS的利用]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#33-xss%E7%9A%84%E5%88%A9%E7%94%A8](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#33-xss的利用))

[3.4 XSS的分类]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#34-xss%E7%9A%84%E5%88%86%E7%B1%BB](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#34-xss的分类))

[3.5 跨站脚本攻击]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#35-%E8%B7%A8%E7%AB%99%E8%84%9A%E6%9C%AC%E6%94%BB%E5%87%BB](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#35-跨站脚本攻击))

[3.6 跨站脚本攻击防护]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#36-%E8%B7%A8%E7%AB%99%E8%84%9A%E6%9C%AC%E6%94%BB%E5%87%BB%E9%98%B2%E6%8A%A4](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#36-跨站脚本攻击防护))

[3.7 XSS防护—Spring MVC]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#37-xss%E9%98%B2%E6%8A%A4spring-mvc](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#37-xss防护spring-mvc))

[四、跨站请求伪造及其安全编码](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#四跨站请求伪造及其安全编码)

[4.1 定义]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#41-%E5%AE%9A%E4%B9%89](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#41-定义))

[4.2 CSRF攻击过程]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#42-csrf%E6%94%BB%E5%87%BB%E8%BF%87%E7%A8%8B](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#42-csrf攻击过程))

[4.3 CSRF的危害]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#43-csrf%E7%9A%84%E5%8D%B1%E5%AE%B3](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#43-csrf的危害))

[4.4 CSRF攻击分析]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#44-csrf%E6%94%BB%E5%87%BB%E5%88%86%E6%9E%90](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#44-csrf攻击分析))

[4.4 CSRF缺陷代码]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#44-csrf%E7%BC%BA%E9%99%B7%E4%BB%A3%E7%A0%81](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#44-csrf缺陷代码))

[4.5 CSRF解决方案]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#45-csrf%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#45-csrf解决方案))

[4.5.1 检测referer]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#451-%E6%A3%80%E6%B5%8Breferer](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#451-检测referer))

[4.5.2 CSRF Token使用原则]([https://github.com/stephenZkang/learn/blob/master/%E5%AE%89%E5%85%A8/Web%E5%AE%89%E5%85%A8.md#452-csrf-token%E4%BD%BF%E7%94%A8%E5%8E%9F%E5%88%99](https://github.com/stephenZkang/learn/blob/master/安全/Web安全.md#452-csrf-token使用原则))

[五、文件上传及其安全编码](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#五、文件上传及其安全编码)

[5.1 非法文件上传](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#5_1_非法文件上传)

[5.2 文件上传防护](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#5_2_文件上传防护)

[六、路径遍历漏洞及其安全编码](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#六、路径遍历漏洞及其安全编码)

[6.1 定义](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#6_1_定义)

[6.2 路径遍历防护](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#6_2_路径遍历防护)

[七、越权漏洞及其安全编码](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#七、越权漏洞及其安全编码)

[7.1 定义](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#7_1_定义)

[7.1.1 垂直越权漏洞](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#7_1_1_垂直越权漏洞)

[7.1.2 水平越权漏洞](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#7_1_2_水平越权漏洞)

[7.2 越权漏洞防护](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#7_2_越权漏洞防护)

[7.2.1 垂直越权漏洞](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#7_2_1_垂直越权漏洞)

[7.2.2 水平越权漏洞](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#7_2_2_水平越权漏洞)

[八、XML外部实体注入及其安全编码](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#八、XML外部实体注入及其安全编码)

[8.1 XXE->XML外部实体注入](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#8_1_XXE>XML外部实体注入)

[8.2 XXE-利用方法](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#8_2_XXE利用方法)

[8.3 XXE-经典漏洞代码](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#8_3_XXE经典漏洞代码)

[8.4 XXE安全防护](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#8_4_XXE安全防护)

[九、业务安全](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#九、业务安全)

[9.1 账户信息安全](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#9_1_账户信息安全)

[9.2 业务数据安全](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#9_2_业务数据安全)

[9.3 业务流程安全](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#9_3_业务流程安全)

[9.4 业务接口安全](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#9_4_业务接口安全)

[9.5 总结](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#9_5_总结)

[十、其它](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#十、其它)

[10.1 IP登录限制绕过](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#10_1_IP登录限制绕过)

[10.2 服务端请求伪造攻击](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#10_2_服务端请求伪造攻击)

[10.3 异常调试信息泄露](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#10_3_异常调试信息泄露)

[10.4 基础框架漏洞](http://blog.nsfocus.net/web-vulnerability-analysis-coding-security/#10_4_基础框架漏洞)

## 一、Web安全基础

### 1.1 常见的Web安全漏洞

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/5ffd4b8626370b81cbe15e106d47ab31.png)

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/934a41683f06ad3869a6a5f6a5e59b72.png)

### 1.2 安全编码原则

**一切输入都是有害的！！！输出也不安全！**

输入：传参，cookie、session、http header、数据库……

输出：异常信息、敏感信息、xss

**没有绝对的安全****……**

### 1.3 数据验证

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/94e202dc6793a6e5c592bfd34dce83b3.png)

### 1.4 身份认证&会话管理

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/96444291e05f47d18375a8b0cf970396.png)

### 1.5 授权管理

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/d54929ed8256fff6fbbdf53c1865d48e.png)

### 1.6 存储安全

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/96a2d74e273808fc68e10eda07f7dfea.png)

## 二、SQL注入及其安全编码

### 2.1 定义

**SQL注入的定义**

由于程序中对用户输入检查不严格，用户可以提交一段数据库查询代码，根据程序返回的结果，获得某些他想得知的数据，这就是所谓的SQL Injection，即SQL注入。

**SQL注入的本质**

对于输入检查不充分，导致SQL语句将用户提交的非法数据当作语句的一部分来执行。

SQL注入漏洞，就是将用户可控的数据拼接进了SQL语句中，一起提交到了数据库执行。

攻击者通过注入语句，改变SQL语句执行逻辑，通过控制部分SQL语句，攻击者可以查询数据库中任何自己需要的数据，利用数据库的一些特性，可以直接获取数据库服务器的系统权限。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/5ebbb0e01e81cdd83e17cbbc951a7934.png)

**某银行信用卡商城SQL注入漏洞**

https://shop.***.com.cn/BusinessCityWeb/ecity.do?func=queryClassFun&dom=<request><queryClass currpage=”1″ rowspage=”20″ sorttype=”0″ brand=”043″ goods_price=”0|300″ goods_nm=”” color=”” type_pid=”” type_id=”” querytype=”brandForColor”/></request>中参数goods_price、brand存在SQL注入漏洞；

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/b3beb86c266839df3e435eb7e4dce3b1.png)**某输入法网站Ajax页面POST型SQL注入漏洞**

该网站的Ajax页面是http://***.***.com/zt/acgn/pc/ajax_post.php，POST内容为：qq=CasterJs&type=pc&nickname=CasterJs&entries=CasterJs，Web应用程序未过滤参数type，导致存在POST型注入漏洞。使用sqlmap工具，可以注入得到数据库名

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/7f90ea1297197842ef8279ccdc5ff153.png)

### 2.2 经典SQL注入代码示例

**1）Servlet示例**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/397cb93d4bfdb8ae660b151f473608b3.png)

**2）mybatis示例**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/d020cca669e23372fcbf6d1d4ac3c1a6.png)

### 2.3 文艺的SQL注入

**a）用户注册页面将用户数据存入数据库**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/f89abdb3c7f47ac66d0e888169ff573f.png)

**b）从数据库中取出用户名，根据用户名查询其他信息**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/b0d0305770699642449977258c3e0b20.png)

### 2.4 SQL注入防护

a)对输入点进行过滤（不是根本解决方法 可能被绕过）

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/524618d0a505d6c91a5edec1725c4c56.png)

建议使用ESAPI针对输入数据进行过滤。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/48cfb4f8895588a0a9f2a90f8ff800db.png)

b）预编译方式访问数据库

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/781845fe53d82a3d0c78bbcbaf2cadbd.png)

预编译本质上也是对参数的过滤，只不过由官方实现。

/JavaVulnerableLab/vulnerability/sqli/download_id.jsp?fileid=1

c）使用存储过程

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/de3208079daf0c032e77229f84d66e72.png)

### 2.5 MyBatis的SQL注入防护

**模糊查询：**

MySQL：select * from table where name like concat(‘%’,#{name},’%’)

Oracle：select * from table where name like ‘%’ || #{name} || ’%’

SQL Server：select * from table where name like ‘%’+#{name}+’%’

DB2：select * from table where name like concat(‘%’,#{name},’%’)

## 三、跨站脚本攻击及其安全编码

### 3.1 定义

XSS（Cross Site Script）漏洞，从本质上来说就是将数据注入到了静态脚本代码中（HTML或者Javascript等），当浏览器渲染整个HTML文档的过程中触发了注入的脚本，导致了XSS攻击的发生。

*String title =* *request.getParameter**(“title”);*

*String id =* *request.getParameter**(“id”);*

*….*

** ***<%=title%>\*****

** ***<%=\******contect\******%>\*****

**XSS不止可以弹窗……**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/918db210342c40b3d167e4ff6e13c7a4.png)

### 3.2 XSS攻击模式

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/e2a7328ee201976cc809215f632b7737.png)

### 3.3 XSS的利用

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/90aa9c83f8c1349dfebb0815ab04b24f.png)

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/3cb7027b02b174328579b86ad76971d0.png)

**某生活网站存在反射型、存储型跨站脚本攻击**

在wap页面的网友中心发表提问页面中，应用程序对用户的输入过滤不严格，导致存在存储型跨站脚本攻击，攻击者在标题处构造跨站脚本”<img src=@ onerror=alert(19)>”

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/8b6b0b4db27c07a94cc792f1c37d6dd4.png)

提交问题后回到“我的帖子”页面，可以看到跨站脚本被执行，弹出“19”窗口，

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/2017704beac19df49bc7433a63c5edcf.png)

### 3.4 XSS的分类

- Reflected XSS (Non-persist XSS)

跨站代码一般存在于链接中，请求这样的链接时，跨站代码经过服务端反射回来，这类跨站的代码一般不存储到服务端

- Stored XSS (Persist XSS)

这是利用起来最方便的跨站类型，跨站代码存储于服务端（比如数据库中）

- DOM based XSS

一种基于DOM的跨站，这是客户端脚本自身解析不正确导致的安全问题

### 3.5 跨站脚本攻击

**a) 存储型跨站脚本攻击**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/862d5f580fc591f2351d21ace3737f96.png)

**b）反射型跨站脚本攻击**

<%out.print(request.getParameter(“param”)); %>

### 3.6 跨站脚本攻击防护

对输出数据使用HtmlEncoder对一些字符做转义处理

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/1e28ac18b4754f42d32044dcde7509e9.png)

**a) 全局拦截 （全局过滤器、拦截器），适用于不包含富文本的情况**

Servlet的doFilter、Spring的Interceptor类，对所有的访问请求进行监听。正确的姿势是在过滤器中对<>&’”=等字符转义处理，可使用ESAPI或者common-lang.jar的StringEscapeUtils类或者Spring的HtmlUtils来实现。

**b)富文本交互，白名单过滤**

ESAPI.validator().getValidSafeHTML(“getValidSafeHTML”, keyword, keyword.length(), true)

白名单：JavaVulnerableLab/vulnerability/xss/xss4.jsp

### 3.7 XSS防护—Spring MVC

**a)项目级过滤**

<context-param>
<param-name>defaultHtmlEscape</param-name>
<param-value>true</param-value>
</context-param>

**b)页面级过滤**

在包含form的jsp页面中添加

<spring:htmlEscape defaultHtmlEscape=”true” />

**c)表单元素级过滤**

在form元素中添加

<form:form htmlEscape=“true”>或

<form:input path=”someFormField” htmlEscape=”true” />

## 四、跨站请求伪造及其安全编码

### 4.1 定义

Cross-site Request Forgery

–在某个恶意站点的页面上，促使访问者请求被攻击网站的某个 URL，从而达到改变服务器端数据的目的

**攻击****者盗用了你的身份，以你的名义发送恶意请求**

–诞生于2000年，火于2007/2008年

–90%的网站存在此类漏洞

–特征为目标站点无token或者referer限制

–利用方式分GET与POST两种

**CSRF 与 XSS的区别？**

Csrf的**破坏力取决于受害者的权限，**与浏览器机制有关。

- CSRF，跨站请求伪造(Cross Site Request Forgery)
- 在用户会话下对某个请求发出GET/POST请求，而请求并非用户自愿发出
- 网站通过cookie识别用户，当用户在某网站成功进行身份验证后，浏览器会得到一个标识其身份的cookie。只要不关闭浏览器或退出登录，以后访问这个网站都会带上这个cookie
- 如果这期间被人诱骗请求了这个网站的url，则相当于发出了身份认证后的请求，可能会执行一些用户不想做的敏感操作

### 4.2 CSRF攻击过程

目标网站：www.webA.com

恶意网站：www.webB.com

转账功能链接：

http://www.webA.com/transport?account=abc&total=500

恶意网站www.webB.com某CSRF页面：

<script>

new Image().src=’http://www.webA.com/transport?account=abc&total=500’;

</script>

诱骗受害用户访问恶意网站CSRF页面

### 4.3 CSRF的危害

- 盗取目标网站上用户隐私数据
- 篡改目标网站上用户数据
- 传播CSRF蠕虫
- …

CSRF的应用与危害要取决于其场景。对于开源的、多用户的、以及社交网站，CSRF攻击带来的后果可能非常严重，且此时CSRF可以直接攻击管理员后台。然而对于闭源的、宣传册式的网站，CSRF造成的危害相对要小的多。因为此时攻击者并不清楚后台的情况，且由于此类网站用户数量很少，基本不能对其他用户产生攻击。

### 4.4 **CSR****F****攻击分析**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/72a278fd0808a235a53aaf3fbce622ee.png)

从上图可以看出，要完成一次CSRF攻击，受害者必须依次完成两个步骤：

1.登录受信任网站A，并在本地生成Cookie。

2.在不登出A的情况下，访问危险网站B。

- 你不能保证你登录了一个网站后，不再打开一个tab页面并访问另外的网站。
- 你不能保证你关闭浏览器了后，你本地的Cookie立刻过期，你上次的会话已经结束。（事实上，关闭浏览器不能结束一个会话，但大多数人都会错误的认为关闭浏览器就等于退出登录/结束会话了……）

3.上图中所谓的攻击网站，可能是一个存在其他漏洞的可信任的经常被人访问的网站

### 4.4 CSRF缺陷代码

**A****站点：**

String user = request.getParameter(“user”);

String pass = request.getParameter(“pass”);

PreparedStatement ps = con.prepareStatement(“update UserTB set password=? Where user=?”);

ps.setString(1,user);

ps.setString(2,pass);

con.executeUpdate();

**恶意****站点上的代码**

GET方式

<img src=http://siteA/updateuser.jsp?user=admin&pass=123456>

POST方式

<form action=http://siteA/updateuser.jsp method=POST>

<input type=”text” name=”user” value=”admin” />

<input type="text" name="pass" value="123456″ />

</form>

<script> document.forms[0].submit(); </script>

### 4.5 CSRF解决方案

- 判断HTTP Referer
- 图形验证码
- **使用隐藏的****tonken** **hash****做****校验**

#### 4.5.1 检测referer

- HTTP Referer是header的一部分，当浏览器向web服务器发送请求的时候，一般会带上Referer，告诉服务器我是从哪个页面链接过来的
- **request.getHeader****(“REFERER****“);**
- 通过检查Referer的值，我们就可以判断这个请求是合法的还是非法的，但是问题出在服务器不是任何时候都能接受到Referer的值，所以Refere Check 一般用于监控CSRF攻击的发生，而不用来抵御攻击。

**合规代码示例**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/62d563a97bc9d607e45934de74b47293.png)

#### 4.5.2 CSRF Token使用原则

- Token要足够随机–只有这样才算不可预测
- Token是一次性的，即每次请求成功后要更新Token–这样可以增加攻击难度，增加预测难度
- Token要注意保密性–敏感操作使用post，防止Token出现在URL中
- 如果同域下存在xss的话，除了验证码，其他的方式都无法防御这个问题。

防护csrf的措施虽然很多，但归根到底就是一条：在客户端提交请求时增加伪造随机数。有个程序后端可能是用REQUEST方式接受的，而程序默认是POST请求，其实改成GET方式请求也可以发送过去，存在很严重的隐患。

## 五、文件上传及其安全编码

### 5.1 非法文件上传

非法文件上传产生的主要原因就是在服务器端没有对用户上传的文件类型做校验或者校验不完整，导致用户可以上传恶意脚本到服务器。

![img](http://blog.nsfocus.net/wp-content/uploads/2017/10/774ec62af22badfcefc1ed7f6440fc95.png)

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/d7f42f4245ebf17dd155c2f86a8d170e.png)

### 5.2 文件上传防护

1、白名单检查文件扩展名，不属于白名单内的，不允许上传。

2、上传文件的保存目录不可解析jsp、php等脚本语言

3、文件名随机命名。如UUID、GUID，不允许用户自定义。

4、如果允许，对上传的图片文件进行渲染

5、记录日志

![img](http://blog.nsfocus.net/wp-content/uploads/2017/10/f6f83b9ca90bdef6b9f9afe98266742e.png)

## 六、路径遍历漏洞及其安全编码

### 6.1 定义

路径遍历漏洞成因：服务器端，接收请求中传来的文件名称，在服务器端拼凑成文件的绝对路径，并且用输出流下载。

![img](http://blog.nsfocus.net/wp-content/uploads/2017/10/d2831eabbafcff321940bcdde683e896.png)

**某网上银行系统任意文件下载漏洞**

访问链接：http://econline.***.com.cn:8080/NASApp/iTreasury-ebank/DownloadFile.web?fileName=/etc/passwd

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/588af5086689eb577f77465227ead77f.png)

### 6.2 路径遍历防护

**方案一：**

1、要下载的文件地址保存至数据库中。

2、文件ID使用随机数命名

3、文件路径保存至数据库，用户提交文件对应ID下载文件。

4、下载文件之前做权限判断。

5、记录文件下载日志。

**方案二：**

针对文件的访问，直接给出文件路径的链接。如：

<a href=“http://xx.xx.xx.xx/upload/file1.jpg”>

## 七、越权漏洞及其安全编码

### 7.1 定义

**垂直越权漏洞**，也称为权限提升，是一种“基于URL的访问控制”设计缺陷引起的漏洞。由于Web应用程序没有做权限控制或者仅在菜单上做了权限控制，导致恶意用户只要猜测其他管理页面的URL，就可以访问或控制其他角色拥有的数据或页面，达到权限提升的目的。

**水平****越权漏洞**，是一种“基于数据的访问控制”设计缺陷引起的漏洞。由于服务器端在接收到请求数据进行操作时没有判断数据的所属人而导致的越权数据访问漏洞。如服务器端从客户端提交的request参数（用户能够控制的数据）中获取用户id，恶意攻击者通过变换请求ID的值，查看或修改不属于本人的数据。

#### 7.1.1 垂直越权漏洞

![img](http://blog.nsfocus.net/wp-content/uploads/2017/10/bea8b9d03e1f8ba6046d51ec2b6dd66b.png)

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/5b8f67a77358d75fff73c08e4d06b272.png)

#### 7.1.2 水平越权漏洞

**越权删除用户地址**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/43cccfe14dcf5ec8e526ba4134164bbc.png)

**某银行水平越权遍历其它账号的余额**

该银行越权漏洞存在于涉及转账汇款的地方，选择付款账户时系统会先查询账户的余额，在此处通过遍历账号即可获取到其他人的账户余额，使用burpsuite的intruder功能遍历accountNo查询他人账户余额

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/6f8eeee5344f07db0df7cc0930fbe3ce.png)

### 7.2 越权漏洞防护

#### 7.2.1 垂直越权漏洞

**垂直越权****漏洞：**在调用功能之前，验证当前用户身份是否有权限调用相关功能（推荐使用过滤器，进行统一权限验证）

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/d4e033c2c186c74a32b625394d3bae07.png)

**垂直越权漏洞****防护方案****：**通过全局过滤器来检测用户是否登录，是否对资源具有访问权限。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/fb97a1b7aadb3d205773b34a02cf21f5.png)

将权限访问规则存入privilege.properties文件中

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/1e39da72d1493856fd707e2eac019586.png)

在web.xml中配置过滤器及权限配置文件。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/7ee9cb54a075e3b99bdf01ed89eb6908.png)

**Spring MVC访问控制**

Spring Security也提供了“基于URL的访问控制”和“基于Method的访问控制”。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/ce3552b4bca09da84c28a62d4f9a1998.png)

#### 7.2.2 水平越权漏洞

**水平****越权****漏洞****：**在用户进行操作时，从session中获取用户id，将传入的参数与用户的身份做绑定校验。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/849cb2239bdb4bb2fe7962d8652ff700.png)

## 八、XML外部实体注入及其安全编码

### 8.1 XXE->XML外部实体注入

XXE（XML External Entity Injection）是一种针对XML终端实施的攻击，黑客想要实施这种攻击，需要在XML的payload包含外部实体声明，且服务器本身允许实体扩展。这样的话，黑客或许能读取WEB服务器的文件系统，通过UNC路径访问远程文件系统，或者通过HTTP/HTTPS连接到任意主机。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/70a64f60bea2150b2d4afb57838900a5.png)

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/4ee9765b4a49805978442ac834fb1f49.png)

### 8.2 XXE-利用方法

XML实体注入产生的根本原因就是在XML1.0标准中引入了“entity”这个概念，且“entity”可以在预定义的文档中进行调用，XXE漏洞的利用就是通过实体的标识符访问本地或者远程内容。

- 带回显的利用方式：直接读取本地文件

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/4c95c7e9d306754f5d933a7e69ad2123.png)

- Bland XXE：服务器禁用了外部实体或者做了过滤或者是显示限制

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/d11ce69cfc0eeed3e57323741e31769d.png)

- 拒绝服务攻击

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/87faf42b97fea2f2adb52c47ec3d5f2a.png)

### 8.3 XXE-经典漏洞代码

**使用****org.w3c.dom****包来解析****xml****数据**

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/19c3e13cdd8653e7159afca3b595c5a1.png)

### 8.4 XXE安全防护

**方案一：**

1. 通过传参的方式发送数据；
2. 后台对数据ESAPI的encoder接口对数据转码处理，然后进行XML数据格式化。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/afe045f38f445dbe344d050040b7c6ee.png)

**方案二：**

通过DocumentBuilderFactory 的setFeature对XML解析进行限制。如：

设置**[http://](http://apache.org/xml/features/disallow-doctype-decl)****[apache.org/xml/features/disallow-doctype-decl](http://apache.org/xml/features/disallow-doctype-decl)****为****true**

   设置**[http://](http://xml.org/sax/features/external-general-entities)****[xml.org/sax/features/external-general-entities](http://xml.org/sax/features/external-general-entities)****为****false**

设置**[http://](http://xml.org/sax/features/external-parameter-entities)****[xml.org/sax/features/external-parameter-entities](http://xml.org/sax/features/external-parameter-entities)****为****false**

   设置**[http://](http://apache.org/xml/features/nonvalidating/load-external-dtd)****[apache.org/xml/features/nonvalidating/load-external-dtd](http://apache.org/xml/features/nonvalidating/load-external-dtd)****为****false**

其他：

setXIncludeAware(false)

setExpandEntityReferences(false)

参考代码：

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/83133d1d25642f51d08419d7b3573c76.png)

## 九、业务安全

在电子银行系统中，除了常规的如SQL，XSS，CSRF、XXE等web漏洞外，更重要的是其业务上的安全。银行业务直接关系到用户的经济利益，因而保证其业务上的安全及其重要。

 **分类**

- 账户信息安全
- 业务数据安全
- 业务流程安全
- 业务接口安全

### 9.1 账户信息安全

账户是一个系统的入口，关系到用户最直接的利益，因而账户的安全在业务安全中占及其重要的地位。账户体系分多个层次，每个环节的漏洞，都将给用户带来极大的损失。

 **分类**

- 信息查询
- 撞库风险
- 弱密码
- 密码找回

密码找回凭证太弱，容易被爆破

密码找回凭证可以从客户端、URL、网页源代码中直接获取

最后提交新密码时修改用户ID为其他ID

跳过验证步骤、找回方式，直接到设置新密码页面

用户登录时依据cookie中的某字段来区分账户

### 9.2 业务数据安全

**金额数据篡改**

抓包修改金额等字段，例如在支付页面抓取请求中商品的金额字段，修改成任意数额的金额并提交，查看能否以修改后的金额数据完成业务流程。

**商品数量篡改**

抓包修改商品数量等字段，将请求中的商品数量修改成任意数额，如负数并提交，查看能否以修改后的数量完成业务流程。

### 9.3 业务流程安全

**顺序执行缺陷**

部分网站逻辑可能是先A过程后B过程然后C过程最后D过程。

用户控制着他们给应用程序发送的每一个请求，因此能够按照任何顺序进行访问。于是，用户就从B直接进入了D过程，就绕过了C。如果C是支付过程，那么用户就绕过了支付过程而买到了一件商品。如果C是验证过程，就会绕过验证直接进入网站程序了。

最后提交新密码时修改用户ID为其他ID

跳过验证步骤、找回方式，直接到设置新密码页面

### 9.4 业务接口安全

**重放攻击**

在短信、邮件调用业务或生成业务数据环节中（类：短信验证码，邮件验证码，订单生成，评论提交等），对其业务环节进行调用（重放）测试。如果业务经过调用（重放）后被多次生成有效的业务或数据结果

 短信炸弹

短信内容篡改

短信收件人任意篡改

邮件炸弹

### 9.5 总结

**怎么防护我们的系统**

- 一切输入都是有害的~
- 输出也不安全~
- 黑名单说不定哪天就被人绕过了~

## 十、其它

### 10.1 IP登录限制绕过

系统根据客户端提交的x-forwarded-for值来判断内网登陆还是外网登陆，当客户端请求携带x-forwarded-for值为127.0.0.1时，可直接使用内网登陆方式登陆系统。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/d82284bd5f88fc5bc65fe39f8113759e.png)

### 10.2 服务端请求伪造攻击

是由于有些应用（网页分享、站长工具、图片搜索等）提供了通过URL 获取其他站点资源的功能，当这种功能没有对协议、网络边界等做好限制，导致这种功能被滥用，攻击者可以利用这种缺陷获取内网敏感数据、DOS 内网服务器、获取内网服务器权限、读取文件等。

1）A 站从浏览器获取到用户输入的URL；

2）A 站根据收到的URL，向B 站发送HTTP 请求获取到响应内容；

3）将收到的内容返回给浏览器。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/2783cf57e3d3f10ce53d7ac75ea17772.png)

**服务端请求伪造攻击防护**

- 过滤内网服务器对公网服务器请求的响应。如果Web应用是获取某一类型的文件，在把返回结果展示给用户之前应先验证返回的信息是否符合文件类型标准，比如返回信息应为图片，如果返回信息是HTML，则停止将返回信息返回客户端。
- 统一错误提示信息，避免用户可以根据错误信息来判断远端服务器的端口状态。
- 在内网服务器的防火墙上限制公网服务器的请求端口为HTTP等协议常用端口，如：80、443、8080、8090。
- 若公网服务器的内网IP与内网无业务通信，建议将公网服务器对应的内网IP列入黑名单，避免应用被用来获取内网数据。
- 内网服务器禁用不必要的协议，仅允许HTTP和HTTPS请求，防止类似于file:///、gopher://、ftp:// 等协议引起的安全问题。

### 10.3 异常调试信息泄露

代码中使用e.printStackTrace()打印异常错误信息，在系统发生异常时，如未自定义错误页面，系统就会将发生异常的详细信息打印出来。

![img](https://raw.githubusercontent.com/stephenZkang/learn/master/img/69da3c29c9af988b34059e2eeff46935.png)

### 10.4 基础框架漏洞

- Struts 2 远程代码执行漏洞
- Java反序列化漏洞