package com.free.framework.core.organization.entity;

import java.io.Serializable;
import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;
import java.util.Date;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Organization extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1505036513265L;

	/**
	 * 
	 */

	private Integer id;

	/**
	 * 
	 */

	private String name;

	/**
	 * 
	 */

	private Integer pid;

	/**
	 * 
	 */

	private Integer orderNum;

	/**
	 * 
	 */

	private String icon;

	/**
	 * 1启用-1停用
	 */

	private String status;

}
