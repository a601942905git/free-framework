package com.free.framework.core.organization.controller.param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;

@Data
public class OrganizationParam extends BaseParam {

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
     * 状态
     */
    private String status;
}