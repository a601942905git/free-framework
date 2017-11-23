package com.free.framework.mq.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author smile
 */
@Component
public class ActiveMQSender {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(String destination, Map<String, Object> headers) {
        jmsMessagingTemplate.convertAndSend(destination, "", headers);
    }
}
