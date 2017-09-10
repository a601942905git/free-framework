package com.free.framework.core.organization.controller.param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;

import java.util.Date;

@Data
public class OrganizationParam extends BaseParam {

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

		/**
		 * 
		 */

	private Date saveDate;

		/**
		 * 
		 */

	private String savePerson;

		/**
		 * 
		 */

	private Date updateDate;

		/**
		 * 
		 */

	private String updatePerson;

}
