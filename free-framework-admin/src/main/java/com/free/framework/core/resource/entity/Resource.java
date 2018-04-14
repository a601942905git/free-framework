package com.free.framework.core.resource.entity;

import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;

/**
 * com.free.framework.core.resource.entity.Resource
 * 资源请求实体类
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Resource extends BaseEntity implements Serializable {

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
	 * 父级名称
	 */
	private String pName;

	/**
	 * 排序编号
	 */

	private Integer orderNum;

	/**
	 * 资源路径
	 */

	private String path;

	/**
	 * 1菜单2操作
	 */

	private String type;

}
