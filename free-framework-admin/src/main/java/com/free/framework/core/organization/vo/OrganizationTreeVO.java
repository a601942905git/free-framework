package com.free.framework.core.organization.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * com.free.framework.core.organization.vo.OrganizationTreeVO
 *
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
@Data
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
    private Integer pid;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 子组装列表信息
     */
    private List<OrganizationTreeVO> organizationTreeVOList = new ArrayList<>();
}
