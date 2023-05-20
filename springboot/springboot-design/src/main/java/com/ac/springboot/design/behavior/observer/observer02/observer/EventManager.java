package com.ac.springboot.design.behavior.observer.observer02.observer;

import com.ac.springboot.design.behavior.observer.observer02.simple.LotteryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件处理类
 * @Author: zhangyadong
 * @Date: 2022/12/18 9:57
 */
public class EventManager {

    public enum EventType{
        MQ,Message
    }

    // 监听器集合
    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation,new ArrayList<>());
        }
    }

    /**
     * @description: 订阅
     * @param: eventType 事件类型
     * @param: listener  监听对象
     * @return: void
     * @author: zhangyadong
     * @date: 2022/12/18 10:05
     */
    public void subscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * @description: 取消订阅
     * @param: eventType 事件类型
     * @param: listener  监听对象
     * @return: void
     * @author: zhangyadong
     * @date: 2022/12/18 10:05
     */
    public void unSubscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    /**
     * @description: 通知方法
     * @param: eventType
     * @param: result
     * @return: void
     * @author: zhangyadong
     * @date: 2022/12/18 10:09
     */
    public void notify(Enum<EventType> eventType, LotteryResult result) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.doEvent(result);
        }
    }
}
