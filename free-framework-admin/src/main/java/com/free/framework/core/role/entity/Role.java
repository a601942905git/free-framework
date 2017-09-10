package com.free.framework.core.role.entity;

import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

/**
 * com.free.framework.core.role.entity.Role
 * 角色
 * @author lipeng
 * @dateTime 2017/9/9 22:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends BaseEntity{

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;
}
