package com.free.framework.mq.handler;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * 消费者处理
 * 所有的消费者都继承该类,通过类型来判断需要调用某一
 * @author smile
 */
public interface ReceiverHandler {

    /**
     * 消息处理
     * @param message
     * @throws JMSException
     */
    void handle(Message message) throws JMSException;
}
