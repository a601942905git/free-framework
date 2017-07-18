package com.free.framework.plateform.constant;

/**
 * Created by Administrator on 2017/6/12.
 */
public enum StatusEnum {
    ENABLE_STATUS("1", "启用"),
    DISENABLE_STATUS("2", "停用");

    private String id;

    private String desc;

    StatusEnum(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
