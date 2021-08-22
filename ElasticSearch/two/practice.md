### 实践问题

#### 1. 环境说明

- openstack一台32g内存，8核CPU，300G硬盘
- 操作系统：中科方德
- jdk环境：openjdk-1.8
- elasticsearch、kibana、logstash选择7.9.1

#### 2. elasticsearch水平扩展验证

- elasticsearch默认不能使用root启动，需要创建elastic账户，如果非要使用root启动，可以将附件test.zip中jar包替换到lib下，仅有7.9.1版本

- 使用命令行启动失败

  ```shell
  #解压elastic压缩包
  cd /opt/es
  tar -zxvf elasticsearch-7.9.1-linux-x86_64.tar.gz
  /elasticsearch-7.9.1
  ./bin/elasticsearch -E node.name=node1 -E cluster.name=myapplication -E path.data=/opt/es/node1 -d
  ./bin/elasticsearch -E node.name=node2 -E cluster.name=myapplication -E path.data=/opt/es/node2 -d
  ./bin/elasticsearch -E node.name=node3 -E cluster.name=myapplication -E path.data=/opt/es/node3 -d
  ./bin/elasticsearch -E node.name=node3 -E cluster.name=myapplication -E path.data=/opt/es/node4 -d
  
  #启动集群不能加入
  ```

- 复制目录修改配置文件

  ```shell
  #/opt/es下安装包复制3次
  cd /opt/es
  cp elasticsearch-7.9.1 elastic-node1
  #编辑配置文件
  cd elastic-node1
  vi config/elasticsearch.yml
  #分别修改下面几项
  cluster.name:my-application
  node.name:node-1
  path.data: /opt/es/elastic-node1/data
  path.logs: /opt/es/elastic-node1/logs
  network.host: 0.0.0.0
  http.port: 9200
  transport.tcp.port: 9300
  discovery.seed_hosts: ["10.66.77.83:9300", "10.66.77.83:9301", "10.66.77.83:9302", "10.66.77.83:9303"]
  cluster.initial_master_nodes: ["10.66.77.83:9300", "10.66.77.83:9301", "10.66.77.83:9302", "10.66.77.83:9303"]
  gateway.recover_after_nodes: 3
  
  #启动
  ./bin/elasticsearch -d
  
  cp elasticsearch-7.9.1 elastic-node2
  cp elasticsearch-7.9.1 elastic-node3
  #修改配置文件，启动node2,node3
  
  #node4是用验证，在没有关闭node1，node2，node3情况下配置node4可以正常加入集群,
  #验证elasticsearch水平扩展方式，将集群化配置项写到制定的配置文件中，使用shell启动加载统一配置文件，无需多次修改，然后分别重启node1，node2，node3，必须一台启动完成再启动其他几台
  cp elasticsearch-7.9.1 elastic-node4
  
  ```

  



#### 3. 不停机进行迁移

- 优化理论参考地址

  - https://www.cnblogs.com/yuanzhenliu/p/11605204.html
  - https://www.cnblogs.com/daynote/p/11076965.html

- 文章中介绍实际环境elastic部署操作系统需要修改的系统配置

  - 文件数设置，ulimit

  - /etc/sysctl.conf 配置

    ```shell
    vm.max_map_count = 262144
    vm.swappiness = 1
    ```

- es内存推荐内存为机器的内存50%，另外50%给lucene进行文件处理，但是最好不要超过32G，有讲解了栈内存，推荐最好不要超过26G 

- 当然，内存对于 Elasticsearch 来说绝对是重要的，它可以被许多内存数据结构使用来提供更快的操作。但是说到这里， 还有另外一个内存消耗大户 非堆内存 （off-heap）：Lucene。

  Lucene 被设计为可以利用操作系统底层机制来缓存内存数据结构。 Lucene 的段是分别存储到单个文件中的。因为段是不可变的，这些文件也都不会变化，这是对缓存友好的，同时操作系统也会把这些段文件缓存起来，以便更快的访问。

  Lucene 的性能取决于和操作系统的相互作用。如果你把所有的内存都分配给 Elasticsearch 的堆内存，那将不会有剩余的内存交给 Lucene。 这将严重地影响全文检索的性能。







