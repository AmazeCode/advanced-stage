package com.ac.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听所有db的过期事件__keyevent@*__:expired"
 *
 * @Author: zhangyadong
 * @Date: 2022/10/24 23:04
 */
@Slf4j
@Component
public class RedisKeysExprieListener extends KeyExpirationEventMessageListener {

    /*
        实现步骤：
        1、创建RedisConfig
        @Bean
        RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

            RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            return container;
        }
        2、创建RedisKeyExpirationListener 监听类,并实现方法
        3、启动项目验证

        redis-cli客户端，发送指定，key过期后观察是否会打印消息
        set order:2022102401 1 ex 2 nx
        set order:2022102402 1 ex 2 nx
        set order:2022102403 1 ex 2 nx
     */
    // @Qualifier("container") 使用该注解避免和RedisConfig中的RedisMessageListenerContainer重复注入报错
    public RedisKeysExprieListener(@Qualifier("container") RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        // 获取到失效的 key，进行取消订单业务处理
        // 由于这里是监听了所有的key，如果只处理特定数据的话，需要做特殊处理
        String expiredKey = message.toString();
        log.info("过期的Key：{}", expiredKey);
    }

    /*
        电商类的业务，一般都会有订单30分钟不支付，自动取消的功能，此时就需要用到定时任务框架，Quartz、xxl-job、elastic-job 是比较常用的 Java 定时任务；
        我们也可以通过 Redis 的定时过期、以及过期key的监听，来实现订单的取消功能；
        Redis key 过期提醒配置
        修改 redis 相关事件配置。找到 redis 配置文件 redis.conf，查看 notify-keyspace-events 配置项，如果没有，
        添加 notify-keyspace-events Ex，如果有值，则追加 Ex，相关参数说明如下：
        K：keyspace 事件，事件以 keyspace@ 为前缀进行发布
        E：keyevent 事件，事件以 keyevent@ 为前缀进行发布
        g：一般性的，非特定类型的命令，比如del，expire，rename等
        $：字符串特定命令
        l：列表特定命令
        s：集合特定命令
        h：哈希特定命令
        z：有序集合特定命令
        x：过期事件，当某个键过期并删除时会产生该事件
        e：驱逐事件，当某个键因 maxmemore 策略而被删除时，产生该事件
        A：g$lshzxe的别名，因此”AKE”意味着所有事件

        不推荐使用(仅仅做学习使用)
        基于这一套机制，确实能够实现订单的超时取消，但是还是不太建议使用，这里仅作为一个思路；原因主要有以下几个：
        1、redis 的过期删除策略是采用定时离线扫描，或者访问时懒性检测删除，并没有办法保证时效性，有可能key已经到期了，
        但Redis并没有扫描到，导致通知的延迟；
        2、消息发送即忘（fire and forget），并不会保证消息的可达性，如果此时服务不在线或者异常，通知就再也收不到了；
     */
}
