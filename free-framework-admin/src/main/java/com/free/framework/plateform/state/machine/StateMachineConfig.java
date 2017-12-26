package com.free.framework.plateform.state.machine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * com.free.framework.plateform.state.machine.StateMachineConfig
 * 状态机配置
 * @author lipeng
 * @dateTime 2017/12/26 21:28
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum> {

    /**
     * 配置状态机状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states
                .withStates()
                // 定义初始状态
                .initial(OrderStatusEnum.UNPAID)
                // 定义状态机中所有的状态
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    /**
     * 配置状态机事件触发订单状态的扭转
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
        transitions
                // 付款事件触发订单状态:未付款--->待发货
                .withExternal()
                .source(OrderStatusEnum.UNPAID)
                .target(OrderStatusEnum.WAITING_FOR_DELIVERY)
                .event(OrderEventEnum.PAY)
                .and()
                // 发货事件触发订单状态:待发货--->待收货
                .withExternal()
                .source(OrderStatusEnum.WAITING_FOR_DELIVERY)
                .target(OrderStatusEnum.WAITING_FOR_RECEIVE)
                .event(OrderEventEnum.DELIVERY)
                .and()
                // 收货事件触发订单状态:待收货--->订单完成
                .withExternal()
                .source(OrderStatusEnum.WAITING_FOR_RECEIVE)
                .target(OrderStatusEnum.FINISH)
                .event(OrderEventEnum.RECEIVE);
    }
}
