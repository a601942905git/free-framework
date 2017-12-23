package com.free.framework.mode.strategy;

import com.free.framework.util.money.MoneyUtils;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.strategy.MediumMemberStrategy
 *
 * @author lipeng
 * @dateTime 2017/12/23 14:20
 */
public class MediumMemberStrategy implements MemberStrategy{

    /**
     * 中级会员享受9折优惠
     */
    public static final BigDecimal DISCOUNT = new BigDecimal(0.9);

    @Override
    public BigDecimal calcMemberPrice(BigDecimal bookPrice) {
        System.out.println("中级会员享受9折优惠");
        return MoneyUtils.multiply(bookPrice, DISCOUNT, 2);
    }
}
