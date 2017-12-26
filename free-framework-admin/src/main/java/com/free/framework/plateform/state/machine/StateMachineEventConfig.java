package com.free.framework.plateform.state.machine;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * com.free.framework.plateform.state.machine.StateMachineEventConfig
 *
 * @author lipeng
 * @dateTime 2017/12/26 21:40
 */
@WithStateMachine
public class StateMachineEventConfig {

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_DELIVERY")
    public void pay() {
        System.out.println("=========================================");
        System.out.println("订单已支付,订单状态从未支付状态变成已支付状态");
        System.out.println("=========================================");
    }

    @OnTransition(source = "WAITING_FOR_DELIVERY", target = "WAITING_FOR_RECEIVE")
    public void delivery() {
        System.out.println("=========================================");
        System.out.println("订单已发货,订单状态从待发货状态变成已发货状态");
        System.out.println("=========================================");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "FINISH")
    public void receive() {
        System.out.println("=========================================");
        System.out.println("订单已收货,订单状态从待收货状态变成已完成状态");
        System.out.println("=========================================");
    }
}
