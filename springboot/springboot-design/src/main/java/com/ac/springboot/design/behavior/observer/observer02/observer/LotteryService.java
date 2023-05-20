package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;

/**
 * 开奖服务接口（主要是进行消息发送和MQ记录，和业务分离开）
 * @Author: zhangyadong
 * @Date: 2022/12/18 10:12
 */
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        // 设置事件的类型
        eventManager = new EventManager(EventManager.EventType.MQ,EventManager.EventType.Message);
        // 订阅
        eventManager.subscribe(EventManager.EventType.Message,new MessageEventListener());
        eventManager.subscribe(EventManager.EventType.MQ,new MQEventListener());
    }

    public LotteryResult lotteryAndMsg(String uId) {
        LotteryResult lottery = lottery(uId);
        // 发送通知
        eventManager.notify(EventManager.EventType.Message,lottery);
        eventManager.notify(EventManager.EventType.MQ,lottery);
        return lottery;
    }

    public abstract LotteryResult lottery(String uId);

    /*
        JDK中对观察者的支持

            JDK中提供了Observable类以及Observer接口它们构成JDK对观察者模式的支持
            java.util.observer接口:该接口中声明了一个方法，它充当抽象观察者，其中声明了一个update方法
            void update(Observable o,Object arg);
            java.util.observable类:充当观察者目标类（被观察类），在该类中定义了一个Vector集合来存储观察者对象，下面是它最重要的3个方法。
            void addObserver(Observer o)方法：用于将新的观察者对象添加到集合中
            void notifyObservers(Object arg)方法：调用集合中的所有观察者对象的update方法，通知它们数据发生改变，通常越晚加入集合的观察者越优先得到通知
            void setChange()方法：用来设置一个boolean类型的内部标志，注明目标对象发生了变化，当它为true时，notifyObservers()才会通知观察者。
            用户可以直接使用Observer和Observable类作为观察者模式的抽象层，再自定义具体观察者类和具体观察目标类，使用JDK中提供的这两个类可以更加方便的实现观察者模式
     */
}
