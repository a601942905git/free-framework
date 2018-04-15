package com.free.framework.core.resource.controller;

/**
 * com.free.framework.core.resource.controller.ResourceControllerMappingUrl
 * 资源请求控制器映射路径
 * @author lipeng
 * @dateTime 2017/9/22 23:40
 */
public interface ResourceControllerMappingUrl {

	String RESOURCE_CONTROLLER = "/resources";

	/**
	* 根路径请求映射路径,用于restful接口
	*/
	String RESOURCE = "/";

	/**
	* 详情请求映射路径
	*/
	String ONE_RESOURCE = "/{id}";

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
	 * 资源树请求映射路径
	 */
	String PAGE_RESOURCE_TREE = "/page/tree";

	/**
	* 列表页返回路径
	*/
	String PAGE_LIST_RETURN = "resource/resource_list";

	/**
	* 详情页面返回路径
	*/
	String PAGE_DETAIL_RETURN = "resource/resource_detail";

	/**
	* 新增页面返回路径
	*/
	String PAGE_ADD_RETURN = "resource/resource_add";

	/**
	* 修改页面返回路径
	*/
	String PAGE_UPDATE_RETURN = "resource/resource_update";

	/**
	 * 组织树返回路径
	 */
	String PAGE_RESOURCE_TREE_RETURN = "resource/resource_tree";
}
