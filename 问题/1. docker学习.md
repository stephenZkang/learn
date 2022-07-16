## 1、Ubuntu在线安装（已验证）

?	https://blog.csdn.net/BingZhongDeHuoYan/article/details/79411479 

## 2、现在安装遇到问题解决（已验证）

?	https://www.cnblogs.com/yun6853992/p/9343816.html 

## 3、离线安装教程

?	https://blog.csdn.net/lovexlsforever/article/details/82155562 

## 4、docker基本使用

?	https://www.runoob.com/docker/ubuntu-docker-install.html 

## 5、【20191126】熟悉的指令

docker run -itd -p 8080:80 ubuntu

## 6、docker安装mysql、安装nginx

?	https://www.runoob.com/docker/docker-install-mysql.html 

## 7、docker安装oracle

?	https://www.jianshu.com/p/51f30378317f 

?	安装oracle需要的硬盘空间大致40G，需要扩展vmvare中ubuntu的磁盘

 	https://www.cnblogs.com/dongry/p/10620894.html 

## 8、【20191126】熟悉的指令

?	docker images	查询镜像

?	docker rmi			删除镜像

?	docker pull	xxx 拉去镜像到本地

?	docker exec -it ubuntu bash	登录启动镜像的命令行

?	Docker 容器连接

?	网络端口映射

?	Docker 容器互联

?	新建网络	

```shell
docker network create -d bridge test-net
```

```shell
docker run -itd --name test1 --network test-net ubuntu /bin/bash
```

```shell
docker run -itd --name test2 --network test-net ubuntu /bin/bash
```

然后两台test1和test2网络便可以互连了

使用Dockerfile 定制镜像，需要熟悉

9. 111