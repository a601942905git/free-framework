package com.free.framework.mode.strategy;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.strategy.PriceContext
 * 价格环境
 *
 * @author lipeng
 * @dateTime 2017/12/23 14:15
 */
public class PriceContext {

    private MemberStrategy memberStrategy;

    public PriceContext(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    public BigDecimal calcMemberPrice(BigDecimal bookPrice) {
        return this.memberStrategy.calcMemberPrice(bookPrice);
    }
}
