### 一、Docker安装使用influxdb(1.8)

1、拉取镜像（）

```javascript
docker search influxdb      # 搜索镜像
docker pull influxdb:1.8    # 拉取镜像，不指定版本会拉去最新的版本
docker images               # 查看拉取的镜像
```

2、初始化容器

```xml
docker run -d -p 8086:8086 --name influxdb1.8 -v /data/docker/influxdb:/var/lib/influxdb --restart=always influxdb:1.8
```

查看容器运行状态

```
docker ps      # 查看运行中的容器
docker ps -a   # 查看所有容器
```

3、进入influxdb容器修改配置

```
docker exec -it influxdb1.8 /bin/bash
```

查找配置文件并修改

```
cd /etc/influxdb/
apt-get update         # 更新apt-get
apt-get install vim    # 安装vim
vim influxdb.conf      # 打开配置文件
```

修改配置内容

```
[data]
1、max-serial-per-database=1000000
 每个数据库允许的最大series数，默认设置是一百万。series 指 tag、measurement、policy 相同的数据集合将该设置更改为0，以允许每个数据库的序列数量不受限制。
若超过则会返回500错误，并提示{“error”:“max series per database exceeded: ”}
2、max-values-per-tag = 100000
设置每一个tag允许的value最大数量，默认10W,设置0可以取消限制。若超过该数值，则会返回错误
[http]
3.auth-enabled = true
```

完整配置文件内容

```
[meta]
  dir = "/var/lib/influxdb/meta"

[data]
  dir = "/var/lib/influxdb/data"
  engine = "tsm1"
  wal-dir = "/var/lib/influxdb/wal"
  max-series-per-database=1000000
  max-values-per-tag=100000

[http]
  auth-enabled=true
```

4、增加用户

```
# 进入容器后，命令行登录数据库
influx -host localhost -port 8086 -database mydb
# 查看用户
show users
# 设置用户名密码
create user "root" with password 'root' with all privileges
# 查看用户是否设置成功
show users
```

重启容器验证用户名密码是否设置成功

```
# 指定用户密码登录数据库
influx -host localhost -port 8086 -database mydb -username 'root' -password 'root'
# 查看用户(能够展示代表登录成功)
show users
# 退出数据库以及容器命令
exit
```

使用相关

```
# 指定查询数据的显示格式 -format
influx -host localhost -port 8086 -database mydb -username 'root' -password 'root' -format json
# 美化Json数据显示 -pretty
influx -host localhost -port 8086 -database mydb -username 'root' -password 'root' -execute 'select * from cpu_load_short' -format json -pretty
# 时间戳精度显示设置 -precision
influx -host localhost -port 8086 -database mydb -username 'root' -password 'root' -execute 'select * from cpu_load_short' -format column -precision ms
```

5、influxdb使用

保留策略相关

查看mydb数据库保留策略

```
show retention policies on mydb
```

设置mydb数据库的保留策略（策略名：rp-one-year）

```
create retention policy "rp-one-year" on "mydb" duration 365d replication 1
```

更改mydb数据库的保留策略

```
alter retention policy "rp-one-year" on "mydb" duration 365d replication 1 default
```

删除保留策略

```
drop retention policy "rp-one-year" on "mydb"
```

表相关

创建表

```sql
> use mydb;
Using database mydb
> show measurements;
{
    "results": [
        {}
    ]
}
> insert devops-idc,host=server01 cpu=23.1,mem=0.63
> show measurements;
{
    "results": [
        {
            "series": [
                {
                    "name": "measurements",
                    "columns": [
                        "name"
                    ],
                    "values": [
                        [
                            "devops-idc"
                        ]
                    ]
                }
            ]
        }
    ]
}
```

查看measurements(表)

```sql
> show measurements;
{
    "results": [
        {
            "series": [
                {
                    "name": "measurements",
                    "columns": [
                        "name"
                    ],
                    "values": [
                        [
                            "devops-idc"
                        ]
                    ]
                }
            ]
        }
    ]
}
```

删除表

```sql
> drop measurement "devops-idc"
```

写入数据

通过INSERT语句和行协议，向表devops-idc中插入3条DevOps环境的时序数据记录，时序数据对应的时间为2019/8/30 17:44:53。

```sql
> insert devops-idc-sz,host=server01 cpu=16.1,mem=0.43 1567158293000000000
> insert devops-idc-sz,host=server02 cpu=23.8,mem=0.63 1567158293000000000
> insert devops-idc-sz,host=server03 cpu=56.3,mem=0.78 1567158293000000000
```

数据查询

```sql
> select * from "devops-idc-sz"
name: devops-idc-sz
time                cpu  host     mem
----                ---  ----     ---
1567158293000000000 16.1 server01 0.43
1567158293000000000 56.3 server03 0.78
1567158293000000000 23.8 server02 0.63
> select * from "devops-idc-sz" where host='server01' and time = 1567158293000000000
name: devops-idc-sz
time                cpu  host     mem
----                ---  ----     ---
1567158293000000000 16.1 server01 0.43
```

更新数据

因为时序数据多写少读的特点，influxdb不支持更新操作，笔者也不建议对时序记录执行更新操作。如在某些特殊场景下，必须对时序数据库记录的指标值进行更新，可以利用“时间戳（Timestamp）和时间序列线（Series）完全相同的时序数据记录，是同一条时序数据记录，新插入的时序数据，会覆盖原有的时序数据记录”的特性，更新时序数据记录的的指标值。

```sql
> insert devops-idc-sz,host=server01 cpu=76.1,mem=0.83 1567158293000000000
> select * from "devops-idc-sz";
name: devops-idc-sz
time                cpu  host     mem
----                ---  ----     ---
1567158293000000000 76.1 server01 0.83
1567158293000000000 56.3 server03 0.78
1567158293000000000 23.8 server02 0.63
> 
```

删除数据

同样，因为时序数据多写少读无更新和批量删除时序数据记录的特点，InfluxDB不支持删除单条时序数据记录。除了通过保留策略周期性的定时删除时序数据记录，InfluxDB还还还支持通过WHERE条件语句、删除时间序列线、删除表、删除数据库、删除分片（Shard）等方式直接批量删除指定的时序数据记录。

（1）、通过WHERE条件语句从指定表中删除时序数据记录，从表devops-idc-sz中，删除标签名为host，标签值为server01、在2019/8/30 17:44:53时间点的时序数据记录。

```sql
> delete from  "devops-idc-sz" where "host"='server01' and  time=1567158293s
```

（2）、通过删除时间序列线删除时序数据记录，删除标签对"host"='server01'对应的时间序列线的所有时序数据记录。

```sql
> drop series from "devops-idc-sz" where "host"='server01'
```

（3）、通过删除指定表删除时序数据记录，删除表devops-ids-sz对应的所有时序数据记录

```sql
> drop measurement "devops-idc-sz"
```

（4）、通过删除指定数据库删除时序数据记录，删除数据库telegraf对应的所有时序数据记录。

```
> drop database "mydb"
```

<br/>

（5）、通过删除指定分片删除时序数据记录，删除分片 3对应的所有时序数据记录。

```sql
> show shards
name: _internal
id database  retention_policy shard_group start_time           end_time             expiry_time          owners
-- --------  ---------------- ----------- ----------           --------             -----------          ------
1  _internal monitor          1           2023-03-12T00:00:00Z 2023-03-13T00:00:00Z 2023-03-20T00:00:00Z 

name: mydb
id database retention_policy shard_group start_time           end_time             expiry_time          owners
-- -------- ---------------- ----------- ----------           --------             -----------          ------
3  mydb     autogen          3           2019-08-26T00:00:00Z 2019-09-02T00:00:00Z 2019-09-02T00:00:00Z 
> drop shard 3
```
