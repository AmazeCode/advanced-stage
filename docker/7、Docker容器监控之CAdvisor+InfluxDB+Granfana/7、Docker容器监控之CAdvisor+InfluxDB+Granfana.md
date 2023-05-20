## 一、原生命令

#### 1、操作

```sh
# 查看运行容器
docker ps
# dockers监控状态
docker stats
```

![截图](638dbc6690998759e2654f4ed4920c76.png)

docker stats命令执行结果

![截图](bb91fcc3c6bcc6026613fe524833da8b.png)

#### 2、问题

通过docker stats命令可以很方便的看到当前宿主机上所有容器的CPU,内存以及网络流量等数据，一般小公司够用了。。。。但是，docker stats统计结果只能是当前宿主机的全部容器，数据资料是实时的，没有地方存储、没有健康指标过线预警等功能

## 二、是什么

#### 1、监控3剑客

1. 一句话
   
   ![截图](c8f692510d1e74ef11ce12607f2ecd74.png)
   
   CAdvisor监控收集+InfluxDB存储数据+Grafana展示图标
2. CAdvisor
   
   ![截图](d2e9de28e9c6dc80c3170741469d286c.png)
3. InfluxDB
   
   ![截图](889fe71ae15c04f775674bd1af2fea14.png)
4. Grafana
   
   ![截图](8c1832e2162663f35fc3c24b326445e3.png)
5. 总结
   
   ![截图](29de30896b85434448871446e4073a4c.png)

#### 3、compose容器编排，一套带走

1. 新建目录
   ```
   mkdir /data/docker/cig
   ```
   
   ![截图](de8294858b346aec8caa2886e4705751.png)
2. 新建3件套组合的docker-compose.yml
   
   版本一：使用link关联容器(link后续会被移除不推荐)
   ```yaml
   version: '3.1'
   
   volumes:
     grafana_data: {}
   
   services:
    influxdb:
     image: tutum/influxdb:0.9
     restart: always
     environment:
       - PRE_CREATE_DB=cadvisor
     ports:
       - "8083:8083"
       - "8086:8086"
     volumes:
       - ./data/influxdb:/data
    cadvisor:
     image: google/cadvisor
     links:
       - influxdb:influxsrv
     command: -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxsrv:8086
     restart: always
     ports:
       - "8080:8080"
     volumes:
       - /:/rootfs:ro
       - /var/run:/var/run:rw
       - /sys:/sys:ro
       - /var/lib/docker/:/var/lib/docker:ro
    grafana:
     user: "104"
     image: grafana/grafana
     restart: always
     links:
       - influxdb:influxsrv
     ports:
       - "3000:3000"
     volumes:
       - grafana_data:/var/lib/grafana
     environment:
       - HTTP_USER=admin
       - HTTP_PASS=admin
       - INFLUXDB_HOST=influxsrv
       - INFLUXDB_PORT=8086
       - INFLUXDB_NAME=cadvisor
       - INFLUXDB_USER=root
       - INFLUXDB_PASS=root
   ```
   
   版本二：使用depends_on和networks关联容器
   ```yaml
   version: '3.1'
   
   volumes:
     grafana_data: {}
   
   services:
    influxdb:
     image: tutum/influxdb:0.9
     restart: always
     environment:
       - PRE_CREATE_DB=cadvisor
     ports:
       - "8083:8083"
       - "8086:8086"
     volumes:
       - ./data/influxdb:/data
     networks:
       - cignetwork
    cadvisor:
     image: google/cadvisor
     # links:
     #  - influxdb:influxsrv
     #command: -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxsrv:8086
     command: -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxdb:8086
     restart: always
     ports:
       - "8080:8080"
     volumes:
       - /:/rootfs:ro
       - /var/run:/var/run:rw
       - /sys:/sys:ro
       - /var/lib/docker/:/var/lib/docker:ro
     depends_on: 
       - influxdb
     networks:
       - cignetwork
    grafana:
     user: "104"
     image: grafana/grafana
     restart: always
     links:
       - influxdb:influxsrv
     ports:
       - "3000:3000"
     volumes:
       - grafana_data:/var/lib/grafana
     environment:
       - HTTP_USER=admin
       - HTTP_PASS=admin
       - INFLUXDB_HOST=influxdb
       - INFLUXDB_PORT=8086
       - INFLUXDB_NAME=cadvisor
       - INFLUXDB_USER=root
       - INFLUXDB_PASS=root
     depends_on:
       - influxdb
       - cadvisor
     networks:
       - cignetwork
   
   
   # 自定义网络
   networks:
     cignetwork:
       driver: bridge
   ```
3. 启动docker-compose文件
   ```sh
   docker-compose up
   ```
   
   ![截图](6a87097654010c113299fc09617c5b3d.png)
4. 查看三个服务容器是否启动
   ```sh
   docker ps
   ```
   
   ![截图](e16046156cfdac77a0837ca8f596296f.png)
5. 测试
   ```
   # 开放相应端口号 cAdvisor 8080 influxDB 8083 grafana 3000
   firewall-cmd --zone=public --add-port=3000/tcp --permanent
   firewall-cmd --zone=public --add-port=8080/tcp --permanent
   firewall-cmd --zone=public --add-port=8083/tcp --permanent
   
   firewall-cmd --reload
   ```

浏览cAdvisor收集服务，http://ip:8080/,第一次访问慢，CAdvisor也有基础的图形展示功能，这里主要用它来做数据采集

![截图](c953fc44de0f400a022b5c9b7d8e0ba3.png)

浏览influxdb存储服务，http://ip:8083

![截图](799a0403ec545739df9d2c3390f5888c.png)

浏览grafana展现服务，http://ip:3000,默认账户密码（admin/admin）

![截图](55b2ce67fe960e3266d4bfed853dbc17.png)

配置步骤

1. 配置数据源
   
   ![截图](72e2b8909ee46458ac46b34f9d417eba.png)
2. 选择InfluxDB数据源
   
   ![截图](124ec7918dc923c6a54193bd17fb8e1f.png)
   
   <br/>
3. 配置细节
   
   ![截图](2cc104fd1069dfc3c637e9a1bc928086.png)
   
   ![截图](42f8014fda1b69946ad83ddfb668f62d.png)
   
   ![截图](776a9002dbfa3474c668831a5c549fc9.png)
4. 配置面板panel
   
   ![截图](34a5866a787f358c4322060d81c07465.png)
   
   ![截图](3f68d11736f47fb84663e53bfd9065f5.png)
   
   ![截图](7e0a10f6783b8855d4e67273861bb01a.png)
   
   ![截图](48efb0f23ad16b42d6585b944a77a22d.png)
   
   ![截图](ab4ed0c6f0355f30c46ce2c23edd8fe8.png)
   
   ![截图](4f5b8bdf29be8be72b619bbc77ffdb18.png)
   
   ![截图](8facf1b824248dfa7890ffbf6f060df8.png)
   
   点进去配置监控维度
   
   ![截图](706ebbba243c46dedf825e039174e5c9.png)
   
   点击Save保存，回到面板就能看到
   
   ![截图](aceae7c253dd4f21cd0a2496f4380458.png)
   
   <br/>
   
   至此监控配置完成
