package com.free.framework.mode.strategy;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.strategy.PrimaryMemberStrategy
 * 初级会员具体策略
 * @author lipeng
 * @dateTime 2017/12/23 14:17
 */
public class PrimaryMemberStrategy implements MemberStrategy{


    @Override
    public BigDecimal calcMemberPrice(BigDecimal bookPrice) {
        System.out.println("初级会员没有优惠");
        return bookPrice;
    }
}
