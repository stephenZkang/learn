## 1��Ubuntu���߰�װ������֤��

?	https://blog.csdn.net/BingZhongDeHuoYan/article/details/79411479 

## 2�����ڰ�װ����������������֤��

?	https://www.cnblogs.com/yun6853992/p/9343816.html 

## 3�����߰�װ�̳�

?	https://blog.csdn.net/lovexlsforever/article/details/82155562 

## 4��docker����ʹ��

?	https://www.runoob.com/docker/ubuntu-docker-install.html 

## 5����20191126����Ϥ��ָ��

docker run -itd -p 8080:80 ubuntu

## 6��docker��װmysql����װnginx

?	https://www.runoob.com/docker/docker-install-mysql.html 

## 7��docker��װoracle

?	https://www.jianshu.com/p/51f30378317f 

?	��װoracle��Ҫ��Ӳ�̿ռ����40G����Ҫ��չvmvare��ubuntu�Ĵ���

 	https://www.cnblogs.com/dongry/p/10620894.html 

## 8����20191126����Ϥ��ָ��

?	docker images	��ѯ����

?	docker rmi			ɾ������

?	docker pull	xxx ��ȥ���񵽱���

?	docker exec -it ubuntu bash	��¼���������������

?	Docker ��������

?	����˿�ӳ��

?	Docker ��������

?	�½�����	

```shell
docker network create -d bridge test-net
```

```shell
docker run -itd --name test1 --network test-net ubuntu /bin/bash
```

```shell
docker run -itd --name test2 --network test-net ubuntu /bin/bash
```

Ȼ����̨test1��test2�������Ի�����

ʹ��Dockerfile ���ƾ�����Ҫ��Ϥ

9. 111