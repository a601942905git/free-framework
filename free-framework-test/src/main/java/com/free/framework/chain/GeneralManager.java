package com.free.framework.chain;

import java.math.BigDecimal;

/**
 * com.free.framework.chain.GeneralManager
 *
 * @author lipeng
 * @dateTime 2017/12/25 21:56
 */
public class GeneralManager extends Handler{

    @Override
    public void handlerRequest(BigDecimal applyMoney) {
        System.out.println("申请的金额大于5000,都得经过总经理来进行审批......");
    }
}
