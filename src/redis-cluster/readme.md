# redis-cluster

redis集群中的每个节点监听两个端口的tcp连接。面向客户端提供服务的redis端口默认为6379，另一个端口一般取数据端口(6379)与10000的和，即16379。
Kubernetes集群内部通过redis-cluster service访问redis服务。
