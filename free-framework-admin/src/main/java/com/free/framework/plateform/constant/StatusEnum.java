package com.free.framework.plateform.constant;

/**
 * com.free.framework.plateform.constant.StatusEnum
 * 状态枚举
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
public enum StatusEnum {
    ENABLE_STATUS("1", "启用"),
    DISABLE_STATUS("2", "停用");

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
