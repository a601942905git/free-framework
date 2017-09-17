package ${basePackage}.${baseCorePackage}.${moduleName}.${servicePackage}.${serviceImplPackage};

<#if module.persistance=="mybatis">
import java.util.List;
</#if>

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.${baseCorePackage}.${moduleName}.${actionPackage}.param.${entityName}Param;
import ${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName};
import ${basePackage}.${baseCorePackage}.${moduleName}.${daoPackage}.${entityName}Mapper;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.util.date.DateUtils;
import com.free.framework.plateform.common.service.CommonService;
import com.github.pagehelper.PageInfo;

import java.util.Date;
/**
 * ${remark!}操作相关
 */
@Service
@Slf4j
public class ${entityName}Service extends CommonService {

	@Autowired
	private ${entityName}Mapper ${entityParamName}Mapper;
	
	/**
	 * 查询${remark!}列表信息
	 * @param ${entityParamName}Param
	 * @return
	 */
	public PageInfo<${entityName}> page${entityName}(${entityName}Param ${entityParamName}Param){
		// 分页
		startPage(${entityParamName}Param);
		// 用户列表
		List<${entityName}> ${entityParamName}List = ${entityParamName}Mapper.list${entityName}(${entityParamName}Param);
		// 设置分页信息
    	PageInfo<${entityName}> page${entityName} = new PageInfo(${entityParamName}List);
        return page${entityName};
	}
	
	/**
	 * 查询${remark!}详情信息
	 * @param ${primaryKey}
	 * @return
	 */
	public ${entityCamelName} get${entityName}(Integer ${primaryKey}){
		return ${entityParamName}Mapper.get${entityName}(${primaryKey});
	}

	/**
	 * 新增保存${remark!}
	 * @param ${entityParamName}
	 */
	public Integer save${entityName}(${entityName} ${entityParamName}){
		${entityParamName}.setSavePerson(UserUtils.getUserLoginCode());
		${entityParamName}.setSaveDate(DateUtils.getCurrentDate());
		${entityParamName}.setStatus(StatusEnum.ENABLE_STATUS.getId());
		return ${entityParamName}Mapper.save${entityName}(${entityParamName});
	}
	
	/**
	 * 修改${remark!}
	 * @param ${entityParamName}
	 */
	public Integer update${entityName}(${entityName} ${entityParamName}){
		${entityParamName}.setUpdatePerson(UserUtils.getUserLoginCode());
		${entityParamName}.setUpdateDate(DateUtils.getCurrentDate());
		return ${entityParamName}Mapper.update${entityName}(${entityParamName});
	}
}
