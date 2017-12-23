package com.free.framework.mode.strategy;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.strategy.MemberStrategy
 * 会员抽象策略
 * @author lipeng
 * @dateTime 2017/12/23 14:16
 */
public interface MemberStrategy {

    /**
     * 计算会员价格
     * @return
     */
    BigDecimal calcMemberPrice(BigDecimal bookPrice);
}
