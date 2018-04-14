package com.free.framework.core.resource.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.free.framework.core.resource.vo.ResourceTreeVO
 * 资源实体类
 * @author lipeng
 * @dateTime 2017/9/22 23:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceTreeVO {

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
    private Integer pId;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 默认不选中
     */
    private String checked = "false";

    /**
     * 默认不展开
     */
    private String open = "false";
}
