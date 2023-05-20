package com.ac.springboot.design.create.builder.builder02;

/**
 * 普通方法（非建造者模式）
 *  使用set方法，进行设置参数
 * @Author: zhangyadong
 * @Date: 2022/11/27 22:12
 */
public class RabbitmqClient2 {

    private String host = "127.0.0.1";

    private int port = 5672;

    private int mode;

    private String exchange;

    private String queue;

    private boolean isDurable = true;

    int connectionTimeout = 1000;

    // 1、私有构造方法
    private RabbitmqClient2() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        if (mode == 1) {
            if (exchange != null) {
                throw new RuntimeException("工作队列模式无需设置交换机");
            }
            if (queue == null || queue.trim().equals("")) {
                throw new RuntimeException("工作队列模式名称不能为空");
            }
            if (isDurable == false) {
                throw new RuntimeException("工作队列模式必须开启持久化");
            }
        } else if (mode == 2) {
            if (exchange != null) {
                throw new RuntimeException("路由模式必须设置交换机");
            }
            if (queue != null) {
                throw new RuntimeException("路由模式无须设置队列名称");
            }
            if (isDurable == false) {
                throw new RuntimeException("路由模式必须开启持久化");
            }
        }

        this.mode = mode;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {

        if (mode == 1) {
            if (exchange != null) {
                throw new RuntimeException("工作队列模式无需设置交换机");
            }
            if (queue == null || queue.trim().equals("")) {
                throw new RuntimeException("工作队列模式名称不能为空");
            }
            if (isDurable == false) {
                throw new RuntimeException("工作队列模式必须开启持久化");
            }
        } else if (mode == 2) {
            if (exchange != null) {
                throw new RuntimeException("路由模式必须设置交换机");
            }
            if (queue != null) {
                throw new RuntimeException("路由模式无须设置队列名称");
            }
            if (isDurable == false) {
                throw new RuntimeException("路由模式必须开启持久化");
            }
        }

        this.exchange = exchange;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public boolean isDurable() {
        return isDurable;
    }

    public void setDurable(boolean durable) {
        isDurable = durable;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void sendMessage(String msg) {
        System.out.println("发送消息.......");
    }

    /*
        set方法的好处是参数设计更加的灵活，但是通过set方式设置对象属性时，对象有可能存在中间状态（无效状态），
        并且进行属性校验时有前后顺序约束，
        破坏了不可变对象的密封性
        怎么保证灵活设置参数又不会存在中间状态呢？答案就是，使用建造者模式
     */
    public static void main(String[] args) {
       RabbitmqClient2 client2 = new RabbitmqClient2();
       client2.setHost("127.0.0.1");
       client2.setQueue("queue");
       client2.setMode(1);
       client2.setDurable(true);
       client2.sendMessage("Test-MSG2");
    }
}
