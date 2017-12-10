package com.free.framework.plateform.mq.handler;

import com.free.framework.mq.constants.ReceiverConstants;
import com.free.framework.mq.handler.ReceiverHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * com.free.framework.plateform.mq.handler.TestReceiverHandler
 * 消费者测试处理器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Component
@Slf4j
public class TestReceiverHandler implements ReceiverHandler{

    /**
     * 当前handler对应的处理类型
     */
    public static final String HANDLER_TYPE = "test_receiver_handler";

    @Override
    public void handle(Message message) throws JMSException {
        String handlerType = message.getStringProperty(ReceiverConstants.HANDLER_TYPE);
        if (!HANDLER_TYPE.equals(handlerType)) {
            return;
        }
        String text = message.getStringProperty("text");
        log.info("进入{}处理器进行消费处理......,接收到的内容为:{}", HANDLER_TYPE, text);
    }
}
