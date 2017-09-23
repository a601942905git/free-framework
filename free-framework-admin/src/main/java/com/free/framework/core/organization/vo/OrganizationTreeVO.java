package com.free.framework.core.organization.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.free.framework.core.organization.vo.OrganizationTreeVO
 *
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationTreeVO {

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
