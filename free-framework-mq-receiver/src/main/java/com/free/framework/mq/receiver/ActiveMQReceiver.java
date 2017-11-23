package com.free.framework.mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
@Slf4j
public class ActiveMQReceiver {

    @JmsListener(destination = "${spring.activemq.admin.destination}", containerFactory = "myFactory")
    public void receive(Message message) {
        try {
            System.out.println("监听消息为===========>" + message.getStringProperty("text"));
        } catch (JMSException e) {
            log.error("ActiveMQReceiver中receive方法异常:{}", e);
        }
    }
}
