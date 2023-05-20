package com.ac.springboot.design.create.builder.builder02;

/**
 * 建造者模式
 *  1、目标类的构造方法要传入一个Builder对象
 *  2、builder类位于目标类的内部，并且是有关static修饰
 *  3、builder类对象提供内置的各种set方法；注意set方法的返回值时builder本身
 *  4、builder类提供一个build()方法，实现目标对象的创建
 * @Author: zhangyadong
 * @Date: 2022/11/27 22:25
 */
public class RabbitmqClient3 {

    // 1、私有构造
    private RabbitmqClient3(Builder builder) {

    }

    // 2、builder类位于目标类的内部，并且是有关static修饰
    public static class Builder {

        // 保证不可变对象的属性的密闭性
        private String host = "127.0.0.1";
        private int port = 5672;
        private int mode;
        private String exchange;
        private String queue;
        private boolean isDurable = true;
        int connectionTimeout = 1000;

        // 3、builder类对象提供内置的各种set方法；注意set方法的返回值时builder本身
        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public Builder setExchange(String exchange) {
            this.exchange = exchange;
            return this;
        }

        public Builder setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public Builder setDurable(boolean durable) {
            isDurable = durable;
            return this;
        }

        public Builder setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        // 4、builder类提供一个build()方法，实现目标对象的创建
        public RabbitmqClient3 build() {
            // 校验逻辑
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

            return new RabbitmqClient3(this);
        }
    }

    public void sendMessage(String msg) {
        System.out.println("发送消息.......");
    }
}
