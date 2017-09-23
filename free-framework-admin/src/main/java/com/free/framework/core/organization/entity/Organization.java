package com.free.framework.core.organization.entity;

import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Organization extends BaseEntity implements Serializable {

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
	 * 图标
	 */

	private String icon;
}
