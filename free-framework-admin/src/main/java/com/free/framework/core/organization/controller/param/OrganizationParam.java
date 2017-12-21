package com.free.framework.core.organization.controller.param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * com.free.framework.core.organization.controller.param.OrganizationParam
 * 组织请求参数
 * @author lipeng
 * @dateTime 2017/9/17 3:33
 */
@Data
@EqualsAndHashCode
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
