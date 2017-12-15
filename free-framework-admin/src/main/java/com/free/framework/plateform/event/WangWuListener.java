package com.free.framework.plateform.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.event.WangWuListener
 *
 * @author lipeng
 * @dateTime 2017/12/15 14:50
 */
@Component
public class WangWuListener implements SmartApplicationListener {

    /**
     * 支持的事件类型
     * @param eventType
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ContentEvent.class;
    }

    /**
     * 支持的目标类型
     * @param sourceType
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == String.class;
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("王五在赵六之后收到新的内容:" + applicationEvent.getSource());
    }
}
