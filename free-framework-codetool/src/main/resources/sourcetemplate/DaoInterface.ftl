package ${basePackage}.${baseCorePackage}.${moduleName!}.${daoPackage};

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ${basePackage}.${baseCorePackage}.${moduleName!}.${entityPackage}.${entityCamelName};
import ${basePackage}.${baseCorePackage}.${moduleName}.${actionPackage}.param.${entityName}Param;

/**
 * ${remark!}操作相关
 */
@Mapper
public interface ${entityName}Mapper {
	
	/**
	 * 新增保存${remark!}
	 * @param ${entityCamelParamName}
	 */
	int save${entityName}(${entityCamelName} ${entityCamelParamName});
	
	/**
	 * 修改${remark!}
	 * @param ${entityCamelParamName}
	 */
	int update${entityName}(${entityCamelName} ${entityCamelParamName});
	
	/**
	 * 查询${remark!}详情信息
	 * @param ${primaryProperty}
	 * @return
	 */
	${entityCamelName} get${entityName}(${primaryPropertyType} ${primaryProperty});
	
	/**
	 * 查询${remark!}列表信息
	 * @param ${entityParamName}Param
	 * @return
	 */
	List<${entityCamelName}> list${entityName}(${entityName}Param ${entityParamName}Param);
}
