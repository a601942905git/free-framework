package ${basePackage}.${baseWebPackage}.${moduleName}.${actionPackage};

/**
 * ${remark!}请求映射路径
 *
 */
public class ${moduleName?cap_first}ControllerMappingURL {

	//控制器映射路径 
	public static final String ${moduleName?upper_case}_CONTROLLER = "/web/${moduleName}/control";
	
	//初始化查询页面数据
	public static final String ${moduleName?upper_case}_CONTROLLER_EXECUTE = "/execute";
	
	//初始化操作返回界面
	public static final String ${moduleName?upper_case}_CONTROLLER_EXECUTE_RETURN = "pages/${moduleName}/${moduleName}";
	
	//查询${remark!}列表
	public static final String GET_${moduleName?upper_case}_LIST = "/get${moduleName?cap_first}List";
	
	//查询${remark!}详情
	public static final String GET_${moduleName?upper_case}_DETAIL = "/get${moduleName?cap_first}Detail";
	
	//新增保存${remark!}
	public static final String ADD_${moduleName?upper_case} = "/add${moduleName?cap_first}";
	
	//修改${remark!}
	public static final String MODIFY_${moduleName?upper_case} = "/modify${moduleName?cap_first}";
	
	//删除${remark!}
	public static final String DELETE_${moduleName?upper_case} = "/del${moduleName?cap_first}";
	
	//修改${remark!}状态
	public static final String MODIFY_${moduleName?upper_case}_STATUS = "/modify${moduleName?cap_first}Status";
}
