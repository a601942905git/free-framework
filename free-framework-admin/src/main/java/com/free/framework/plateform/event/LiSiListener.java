package com.free.framework.plateform.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.event.LiSiListener
 *
 * @author lipeng
 * @dateTime 2017/12/15 14:33
 */
@Component
public class LiSiListener implements ApplicationListener<ApplicationEvent>{

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContentEvent) {
            System.out.println("李四收到了新的内容：" + applicationEvent.getSource());
        }
    }
}
