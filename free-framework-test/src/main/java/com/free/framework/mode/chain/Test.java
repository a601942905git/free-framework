package com.free.framework.mode.chain;

import java.math.BigDecimal;

/**
 * com.free.framework.mode.chain.Test
 *
 * @author lipeng
 * @dateTime 2017/12/25 21:58
 */
public class Test {

    public static void main(String[] args) {
        // 组建责任链
        Handler projectManagerHandler = new ProjectManager();
        Handler deptManagerHandler = new DeptManager();
        Handler generalManagerHandler = new GeneralManager();
        projectManagerHandler.setSuccessor(deptManagerHandler);
        deptManagerHandler.setSuccessor(generalManagerHandler);

        //projectManagerHandler.handlerRequest(new BigDecimal(500));
        //projectManagerHandler.handlerRequest(new BigDecimal(2000));
        projectManagerHandler.handlerRequest(new BigDecimal(8000));
    }
}
