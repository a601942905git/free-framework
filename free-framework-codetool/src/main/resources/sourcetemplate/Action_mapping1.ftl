package ${basePackage}.${baseCorePackage}.${moduleName}.${actionPackage};

/**
 * ${remark!}请求映射路径
 *
 */
public interface ${moduleName?cap_first}ControllerMappingUrl {

	String ${entityName?upper_case}_CONTROLLER = "/${entityParamName}s";

	/**
	* 根路径请求映射路径,用于restful接口
	*/
	String ${entityName?upper_case} = "/";

	/**
	* 详情请求映射路径
	*/
	String ONE_${entityName?upper_case} = "/{id}";

	/**
	* 新增请求映射路径
	*/
	String PAGE_ADD = "/page/add";

	/**
	* 修改请求应映射路径
	*/
	String PAGE_UPDATE = "/page/update";

	/**
	* 列表页返回路径
	*/
	String PAGE_LIST_RETURN = "${entityParamName}/${entityParamName}_list";

	/**
	* 详情页面返回路径
	*/
	String PAGE_DETAIL_RETURN = "${entityParamName}/${entityParamName}_detail";

	/**
	* 新增页面返回路径
	*/
	String PAGE_ADD_RETURN = "${entityParamName}/${entityParamName}_add";

	/**
	* 修改页面返回路径
	*/
	String PAGE_UPDATE_RETURN = "${entityParamName}/${entityParamName}_update";
}
