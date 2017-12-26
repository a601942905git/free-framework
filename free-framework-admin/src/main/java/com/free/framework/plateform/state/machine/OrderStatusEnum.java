package com.free.framework.plateform.state.machine;

/**
 * com.free.framework.plateform.state.machine.OrderStatusEnum
 * 订单状态枚举类
 * @author lipeng
 * @dateTime 2017/12/26 21:15
 */
public enum OrderStatusEnum {

    /**
     * 已下单,未支付
     */
    UNPAID,

    /**
     * 已付款,待发货
     */
    WAITING_FOR_DELIVERY,

    /**
     * 已发货,待收货
     */
    WAITING_FOR_RECEIVE,

    /**
     * 已收货,订单完成
     */
    FINISH


}
