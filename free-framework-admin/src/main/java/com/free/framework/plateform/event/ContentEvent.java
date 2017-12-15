package com.free.framework.plateform.event;

import org.springframework.context.ApplicationEvent;

/**
 * com.free.framework.plateform.event.ContentEvent
 *
 * @author lipeng
 * @dateTime 2017/12/15 14:33
 */
public class ContentEvent extends ApplicationEvent{

    public ContentEvent(Object source) {
        super(source);
    }
}
