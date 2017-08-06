package com.free.framework.util.http.em;


/**
 * com.free.framework.util.http.em.MediaTypeEnum
 *
 * @author lipeng
 * @dateTime 2017/8/6 13:19
 */
public enum MediaTypeEnum {
    FORM(1, "form"), JSON(2, "json"), FILE(3, "file");

    /**
     * 编号
     */
    private Integer id;

    /**
     * 请求的类型
     */
    private String type;

    MediaTypeEnum(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
