package com.java.state.machine;

import com.free.framework.Application;
import com.free.framework.plateform.state.machine.OrderEventEnum;
import com.free.framework.plateform.state.machine.OrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * com.java.state.machine.StateMachineTest
 *
 * @author lipeng
 * @dateTime 2017/12/26 21:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StateMachineTest {

    @Autowired
    StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine;

    @Test
    public void testStateMachine() {
        stateMachine.start();
        stateMachine.sendEvent(OrderEventEnum.PAY);
        stateMachine.sendEvent(OrderEventEnum.DELIVERY);
        stateMachine.sendEvent(OrderEventEnum.RECEIVE);
    }
}
