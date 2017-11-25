package com.free.framework.mq.receiver;

import com.free.framework.mq.handler.ReceiverHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Map;

/**
 * @author smile
 */
@Component
@Slf4j
public class ActiveMQReceiver {

    @Autowired
    ApplicationContext applicationContext;

    @JmsListener(destination = "${spring.activemq.admin.destination}", containerFactory = "myFactory")
    public void receive(Message message) {
        Map<String, ReceiverHandler> beanMap = applicationContext.getBeansOfType(ReceiverHandler.class);
        if (CollectionUtils.isEmpty(beanMap)) {
            log.warn("ActiveMQReceiver消费者中不存在ReceiverHandler");
            return;
        }

        beanMap.forEach((key, handler) -> {
            try {
                handler.handle(message);
            } catch (JMSException e) {
                log.error("{}处理器消费消息异常:{}", key, e);
            }
        });
    }
}
