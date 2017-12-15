package com.free.framework.plateform.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.event.ZhangSanListener
 *
 * @author lipeng
 * @dateTime 2017/12/15 14:36
 */
@Component
public class ZhangSanListener implements ApplicationListener<ContentEvent>{

    @Override
    public void onApplicationEvent(ContentEvent contentEvent) {
        System.out.println("张三收到了新的内容：" + contentEvent.getSource());
    }
}
