package com.free.framework.core.resource.entity;

import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

/**
 * com.free.framework.core.resource.entity.Resource
 * 资源
 * @author lipeng
 * @dateTime 2017/9/9 23:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Resource extends BaseEntity{

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
     * 路径
     */
    private String path;

    /**
     * 类型
     * 1.菜单
     * 2.功能(增删改查按钮)
     */
    private String type;
}
