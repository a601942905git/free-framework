package com.free.framework.mode.strategy;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.strategy.Test
 *
 * @author lipeng
 * @dateTime 2017/12/23 14:29
 */
public class Test {

    /**
     * 图书的价格
     */
    public static final BigDecimal BOOK_PRICE = new BigDecimal(98.68);

    public static void main(String[] args) {
        // 初级会员
        MemberStrategy memberStrategy = new PrimaryMemberStrategy();
        PriceContext priceContext = new PriceContext(memberStrategy);
        priceContext.calcMemberPrice(BOOK_PRICE);

        // 中级会员
        memberStrategy = new MediumMemberStrategy();
        priceContext = new PriceContext(memberStrategy);
        priceContext.calcMemberPrice(BOOK_PRICE);

        // 高级会员
        memberStrategy = new AdvanceMemberStrategy();
        priceContext = new PriceContext(memberStrategy);
        priceContext.calcMemberPrice(BOOK_PRICE);

    }
}
