package com.free.framework.core.organization.controller;

/**
 * com.free.framework.core.organization.controller.param.OrganizationControllerMappingUrl
 * 组织请求控制器映射路径
 * @author lipeng
 * @dateTime 2017/9/17 3:33
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
	 * 列表请求映射路径
	 */
	String PAGE_LIST = "/page/list";

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
	String PAGE_ORGANIZATION_TREE = "/page/tree";

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
