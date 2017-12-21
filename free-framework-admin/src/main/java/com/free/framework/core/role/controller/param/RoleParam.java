package com.free.framework.core.role.controller.param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * com.free.framework.core.role.controller.param.RoleParam
 * 角色请求参数
 * @author lipeng
 * @dateTime 2017/9/9 23:40
 */
@Data
@EqualsAndHashCode
public class RoleParam extends BaseParam{

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private String status;
}
