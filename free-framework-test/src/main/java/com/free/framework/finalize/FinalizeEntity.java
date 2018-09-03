package com.free.framework.finalize;

/**
 * com.free.framework.finalize.FinalizeEntity
 *
 * @author lipeng
 * @dateTime 2018/9/3 下午3:10
 */
public class FinalizeEntity {

    private Integer id;

    public FinalizeEntity(Integer id) {
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("FinalizeEntity" + this.id + "被回收");
        super.finalize();
    }
}
