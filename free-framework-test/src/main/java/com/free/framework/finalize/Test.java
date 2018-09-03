package com.free.framework.finalize;

/**
 * com.free.framework.finalize.Test
 *
 * @author lipeng
 * @dateTime 2018/9/3 下午3:11
 */
public class Test {

    public static void main(String[] args) {
        FinalizeEntity finalizeEntity1 = new FinalizeEntity(10001);
        FinalizeEntity finalizeEntity2 = new FinalizeEntity(10002);
        FinalizeEntity finalizeEntity3 = new FinalizeEntity(10003);

        finalizeEntity1 = null;
        finalizeEntity2 = null;

        System.gc();
    }
}
