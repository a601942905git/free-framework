package com.free.framework.core.resource.controller.param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * com.free.framework.core.resource.controller.param.ResourceParam
 * 资源请求参数
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
@Data
@EqualsAndHashCode
public class ResourceParam extends BaseParam {

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
     * 1菜单2操作
     */

    private String type;

    /**
     * 状态
     */
    private String status;
}
