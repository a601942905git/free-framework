package com.free.framework.chain;

import java.math.BigDecimal;

/**
 * com.free.framework.chain.DeptManager
 *
 * @author lipeng
 * @dateTime 2017/12/25 21:55
 */
public class DeptManager extends Handler{

    public static final BigDecimal DEFAULT_MONEY = new BigDecimal(5000);

    @Override
    public void handlerRequest(BigDecimal applyMoney) {
        if (applyMoney.compareTo(DEFAULT_MONEY) < 0) {
            System.out.println("申请的金额小于5000,部门经理批准了......");
            return;
        }

        // 如果持有后继对象,那么请求继续往下传递
        if (null != successor) {
            successor.handlerRequest(applyMoney);
        }
    }
}
