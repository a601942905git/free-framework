package com.free.framework.plateform.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/3.
 */
@Data
public class BaseEntity implements Serializable{

    /**
     * 保存时间
     */
    private Date saveDate;

    /**
     * 保存人
     */
    private String savePerson;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updatePerson;

    /**
     * 状态
     */
    private String status;
}
