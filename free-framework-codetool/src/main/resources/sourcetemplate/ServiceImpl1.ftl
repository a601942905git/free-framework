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
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.util.date.DateUtils;
import com.free.framework.plateform.common.service.CommonService;
import com.github.pagehelper.PageInfo;
import java.util.Date;

import java.util.Date;
/**
 * ${remark!}操作相关
 */
@Service
@Slf4j
public class ${entityName}Service extends CommonService<${entityName}> {

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
        return getPageInfo(${entityParamName}List);
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
	public ResponseData save${entityName}(${entityName} ${entityParamName}){
		${entityParamName}.setSavePerson(UserUtils.getUserLoginCode());
		${entityParamName}.setSaveDate(new Date());
		${entityParamName}.setStatus(StatusEnum.ENABLE_STATUS.getId());
		int count = ${entityParamName}Mapper.save${entityName}(${entityParamName});
		return count == 1 ? ResponseData.success() : ResponseData.fail();
	}
	
	/**
	 * 修改${remark!}
	 * @param ${entityParamName}
	 */
	public ResponseData update${entityName}(${entityName} ${entityParamName}){
		${entityParamName}.setUpdatePerson(UserUtils.getUserLoginCode());
		${entityParamName}.setUpdateDate(new Date());
		int count = ${entityParamName}Mapper.update${entityName}(${entityParamName});
		return count == 1 ? ResponseData.success() : ResponseData.fail();
	}
}
