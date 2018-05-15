## Mysql Cluster
mysql cluster是可扩容的m/s(master/slave)集群，同一时刻存在一个master节点以及若干个slave节点。所有写入操作由master节点处理，通过sidecar同步到slave节点。

## Client
* 运行mysql client docker访问
kubectl run -it --rm --image=mysql:5.7 --restart=Never mysql-client -- mysql -h mysql -ppassword
* 登录mysql服务节点进行操作
kubectl exec mysql-0 -ti -- /bin/bash
