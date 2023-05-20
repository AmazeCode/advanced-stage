package com.ac.springboot.design.create.builder.builder02;

/**
 * 普通写法（不使用建造者模式）
 *  构造方法创建实例，参数过多时，传参容易错误
 * @Author: zhangyadong
 * @Date: 2022/11/27 21:56
 */
public class RabbitmqClient1 {

    private String host = "127.0.0.1";

    private int port = 5672;

    private int mode;

    private String exchange;

    private String queue;

    private boolean isDurable = true;

    int connectionTimeout = 1000;

    public RabbitmqClient1(String host, int port, int mode, String exchange, String queue, boolean isDurable, int connectionTimeout) {
        this.host = host;
        this.port = port;
        this.mode = mode;
        this.exchange = exchange;
        this.queue = queue;
        this.isDurable = isDurable;
        this.connectionTimeout = connectionTimeout;

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

        // 其他验证方式
    }

    public void sendMessage(String msg) {
        System.out.println("发送消息.......");
    }

    /*
        缺点:参数过多构造方法过于复杂，不优雅
     */
    public static void main(String[] args) {
        // 每一种模式，都需要根据不同的情况进行实例化，构造方法会变得过于复杂
        RabbitmqClient1 client1 = new RabbitmqClient1("127.0.0.1",5672,
                2,"sample-exchange",null,true,5000);

        client1.sendMessage("Test-MSG");
    }
}
