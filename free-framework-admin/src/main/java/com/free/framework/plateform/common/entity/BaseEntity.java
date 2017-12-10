package com.free.framework.plateform.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * com.free.framework.plateform.common.entity.BaseEntity
 * 公用实体类
 * @author lipeng
 * @dateTime 2017/9/17 3:19
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
