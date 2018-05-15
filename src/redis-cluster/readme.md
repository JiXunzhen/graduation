# redis-cluster

redis集群中的每个节点监听两个端口的tcp连接。面向客户端提供服务的redis端口默认为6379，另一个端口一般取数据端口(6379)与10000的和，即16379。

### redis.conf
* `bind 127.0.0.1` 只接受本地流量
* `protected-mode yes` 在kubernetes集群中，所有服务包括基础组件处于同一内网中，尽管理论上没有外部流量可以攻击至内部集群中，使用protected-mode也可以防止开发人员某些误操作造成的大面积的集群不可用。
