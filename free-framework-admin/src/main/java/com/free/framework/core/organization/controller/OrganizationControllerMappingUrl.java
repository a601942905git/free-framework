package com.free.framework.core.organization.controller;

/**
 * 请求映射路径
 *
 */
public interface OrganizationControllerMappingUrl {

	String ORGANIZATION_CONTROLLER = "/organizations";

	/**
	* 根路径请求映射路径,用于restful接口
	*/
	String ORGANIZATION = "/";

	/**
	* 详情请求映射路径
	*/
	String ONE_ORGANIZATION = "/{id}";

	/**
	* 新增请求映射路径
	*/
	String PAGE_ADD = "/page/add";

	/**
	* 修改请求应映射路径
	*/
	String PAGE_UPDATE = "/page/update";

	/**
	 * 组织树请求映射路径
	 */
	String PAGE_ORGANIZATION_TREE = "/page/organization/tree";

	/**
	* 列表页返回路径
	*/
	String PAGE_LIST_RETURN = "organization/organization_list";

	/**
	* 详情页面返回路径
	*/
	String PAGE_DETAIL_RETURN = "organization/organization_detail";

	/**
	* 新增页面返回路径
	*/
	String PAGE_ADD_RETURN = "organization/organization_add";

	/**
	* 修改页面返回路径
	*/
	String PAGE_UPDATE_RETURN = "organization/organization_update";

	/**
	 * 组织树返回路径
	 */
	String PAGE_ORGANIZATION_TREE_RETURN = "organization/organization_tree";
}
