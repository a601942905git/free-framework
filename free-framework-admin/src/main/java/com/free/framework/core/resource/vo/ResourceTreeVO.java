package com.free.framework.core.resource.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * com.free.framework.core.resource.vo.ResourceTreeVO
 *
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
    private Integer pid;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 子资源列表信息
     */
    private List<ResourceTreeVO> resourceTreeVOList = new ArrayList<>();
}
