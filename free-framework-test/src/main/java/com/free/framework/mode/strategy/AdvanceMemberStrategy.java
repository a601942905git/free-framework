package com.free.framework.mode.strategy;

import com.free.framework.util.money.MoneyUtils;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.strategy.AdvanceMemberStrategy
 * 高级会员具体策略
 * @author lipeng
 * @dateTime 2017/12/23 14:27
 */
public class AdvanceMemberStrategy implements MemberStrategy{

    /**
     * 高级会员享受8折优惠
     */
    public static final BigDecimal DISCOUNT = new BigDecimal(0.8);

    @Override
    public BigDecimal calcMemberPrice(BigDecimal bookPrice) {
        System.out.println("高级会员享受8折优惠");
        return MoneyUtils.multiply(bookPrice, DISCOUNT, 2);
    }
}
