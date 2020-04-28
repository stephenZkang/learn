## git把本地项目传到github

### **利用git把本地项目传到github中**

1、打开git bash命令行，进入到要上传的项目中，比如Spring项目，在此目录下执行git init 的命令，会发下在当前目录中多了一个.git的文件夹（是隐藏的）

2、执行git status，出现如下红色，提示要用git add命令将要上传的文件加入进来。

 ![img](https://images2017.cnblogs.com/blog/1170065/201709/1170065-20170928142014747-393539189.png)

3、执行git add .，没有回显，再次执行git status，出现如下绿色，表明添加成功。

![img](https://images2017.cnblogs.com/blog/1170065/201709/1170065-20170928142320747-1499055252.png)

4、执行git commit -m "提示内容"，开始提交项目，引号中的内容是该项目的解释。

![img](https://images2017.cnblogs.com/blog/1170065/201709/1170065-20170928142354762-2106042415.png)

5、如果你的项目还没有关联SSH密钥，则需要先设置SSH密钥，步骤如下：

　　1）制作密钥对，参考http://www.cnblogs.com/handongyu/p/6386789.html

　　　　![img](https://images2017.cnblogs.com/blog/1170065/201709/1170065-20170928142803872-1759807225.png)

　　2）制作完成之后，需将密钥添加到github中，进行绑定。参考http://www.cnblogs.com/superGG1990/p/6844952.html

　　　　![img](https://images2017.cnblogs.com/blog/1170065/201709/1170065-20170928143100325-450500734.png)

6、如果已经设置好密钥，可以开始绑定你要上传的仓库了，先去你的github主页，新建一个仓库，将仓库的https地址复制，然后执行git remote add origin +你复制的地址，正常情况下没有回显。

7、执行git push -u origin master，将项目push到你的仓库中，出现如下画面则大功告成。

　　![img](https://images2017.cnblogs.com/blog/1170065/201709/1170065-20170928144040606-143165987.png)

 

 

### **将github中已有项目从本地上传更新**

1.进入到要更新上传的项目

2.执行git status , 会发现未上传更新的文件呈现红色，再次执行git add . , 再次执行git status ， 发现文件都上传呈现绿色

3.`git commit -m "提示内容" ```

4.git push -u origin master 

ps：更新不需要绑定获取项目url。例子如下：

![img](https://images2017.cnblogs.com/blog/1170065/201710/1170065-20171027110950461-601748295.png)

![img](https://images2017.cnblogs.com/blog/1170065/201710/1170065-20171027111014351-428996145.png)

![img](https://images2017.cnblogs.com/blog/1170065/201710/1170065-20171027111033023-946521494.png)

![img](https://images2017.cnblogs.com/blog/1170065/201710/1170065-20171027111050586-166175500.png)

###  本地代码上传到Github库的文件夹下

1、下载Github库到本地

```shell
git clone url
```

2、将本地代码复制到下载的目录中

3、使用命令查询未上传的文件

```shell
git status
```

4、使用命令提交代码

```shell
git commit -m "注释"
```

5、使用命令推送到Github仓库

```shell
git push -u origin master
```

6、登录Github就会看到对应的代码

### **github图片显示慢**
将下面的内容复制到本地host文件（C:\Windows\System32\drivers\etc）
192.30.253.112 github.com

192.30.253.112 www.github.com

151.101.64.133 assets-cdn.github.com

151.101.184.133 github.map.fastly.net

151.101.40.133 avatars0.githubusercontent.com

151.101.40.133 avatars1.githubusercontent.com

151.101.40.133 avatars2.githubusercontent.com

151.101.0.133 camo.githubusercontent.com

34.230.182.100 collector.githubapp.com

151.101.185.194 github.global.ssl.fastly.net

54.231.120.3 github-cloud.s3.amazonaws.com

54.231.120.3 s3-1-w.amazonaws.com


复制完cmd执行 ipconfig /flushdns
