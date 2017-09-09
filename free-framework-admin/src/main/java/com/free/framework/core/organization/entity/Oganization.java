package com.free.framework.core.organization.entity;

import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

/**
 * com.free.framework.core.organization.entity.Oganization
 * 组织
 * @author lipeng
 * @dateTime 2017/9/9 22:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Oganization extends BaseEntity{

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级编号
     */
    private Integer pid;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 图标
     */
    private String icon;
}
