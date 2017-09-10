package ${basePackage}.${baseCorePackage}.${moduleName}.${servicePackage};

import java.util.List;
import java.util.Map;
import com.dremoon.framework.common.webmanager.vo.returns.BaseWebReturn;
import com.dremoon.plateform.util.sqlhelper.LimitPager;

import ${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName};

/**
 * ${remark!}操作相关
 */
public interface ${entityName}Service {
	
	/**
	 * 新增保存${remark!}
	 * @param info
	 */
	public BaseWebReturn add${entityName}(${entityCamelName} ${entityCamelParamName});
	
	/**
	 * 修改${remark!}
	 * @param info
	 */
	public BaseWebReturn modify${entityName}(${entityCamelName} ${entityCamelParamName});
	
	/**
	 * 修改${remark!}状态
	 * @param info
	 */
	public BaseWebReturn modify${entityName}Status(${entityCamelName} ${entityCamelParamName});

	/**
	 * 查询${remark!}详情信息
	 * @param placeId
	 * @return
	 */
	public ${entityCamelName} get${entityName}Detail(${primaryPropertyType} ${primaryProperty});
	
	/**
	 * 查询${remark!}列表信息
	 * @param
	 * @return
	 */
	public Map<String,Object> get${entityName}List(Map<String,Object> params,LimitPager limitPager);	
}
