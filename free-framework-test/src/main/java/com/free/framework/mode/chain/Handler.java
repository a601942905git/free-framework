package com.free.framework.mode.chain;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.chain.Handler
 *
 * @author lipeng
 * @dateTime 2017/12/25 21:41
 */
public abstract class Handler {

    /**
     * 持有后继责任对象
     */
    protected Handler successor;

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 处理请求的方法
     * @return
     */
    public abstract void handlerRequest(BigDecimal applyMoney);

}
