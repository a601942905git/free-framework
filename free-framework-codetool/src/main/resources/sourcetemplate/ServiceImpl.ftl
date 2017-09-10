package ${basePackage}.${baseCorePackage}.${moduleName}.${servicePackage}.${serviceImplPackage};

import java.util.Map;
import java.util.HashMap;
<#if module.persistance=="mybatis">
import java.util.List;
</#if>
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dremoon.framework.common.core.service.DicitionaryConstants;
import com.dremoon.framework.common.core.service.DremoonBaseServiceImp;
import com.dremoon.framework.common.webmanager.vo.returns.BaseWebReturn;
import com.dremoon.plateform.core.common.util.DictionaryDetailUtil;
import com.dremoon.framework.common.core.service.constants.ServiceResultConstants;
import com.dremoon.plateform.core.webuser.entity.GmUserInfoEntity;
import com.dremoon.framework.util.il8n.LanguageUtil;

import ${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName};
import ${basePackage}.${baseCorePackage}.${moduleName}.${servicePackage}.${entityName}Service;
import ${basePackage}.${baseCorePackage}.${moduleName}.${daoPackage}.${entityName}Mapper;

/**
 * ${remark!}操作相关
 */
@Service
public class ${entityName}ServiceImp extends DremoonBaseServiceImp<GmUserInfoEntity> implements ${entityName}Service {
	
	Logger logger = LoggerFactory.getLogger(${entityName}ServiceImp.class);

	@Autowired
	private ${entityName}Mapper ${entityParamName}Mapper;
	
	/**
	 * 查询${remark!}列表信息
	 * @param
	 * @return
	 */
	@Override
	public List<${entityCamelName}> get${entityName}List(){
		//分页处理
		startPage();
		Map<String,String> map = new HashMap<String,String>();
		map.put("lockStatus", "lockStatusDesc");
		//查询列表信息
		return DictionaryDetailUtil.setDictionaryData(${entityParamName}Mapper.get${entityName}List(),map);
	}
	
	/**
	 * 查询${remark!}详情信息
	 * @param placeId
	 * @return
	 */
	@Override
	public ${entityCamelName} get${entityName}Detail(${primaryPropertyType} ${primaryProperty}){
		return ${entityParamName}Mapper.get${entityName}Detail(${primaryProperty});
	}

	/**
	 * 新增保存${remark!}
	 * @param info
	 */
	@Override
	public BaseWebReturn add${entityName}(${entityCamelName} ${entityCamelParamName}){
		//定义返回结果对象
		BaseWebReturn baseWebReturn = new BaseWebReturn();
		//获取当前操作的用户
		GmUserInfoEntity gmUserInfoEntity = getCurrentUserInfo();
		if(null != gmUserInfoEntity){
			//新增用户编号
			${entityCamelParamName}.setCreateUser(gmUserInfoEntity.getUserCode());
			//新增用户名称
			${entityCamelParamName}.setCreateUserDesc(gmUserInfoEntity.getUserName());
		}
		//新增时间
		${entityCamelParamName}.setCreateDate(new Date());
		//删除状态
		${entityCamelParamName}.setDelStatus(DicitionaryConstants.DELETE_STATUS_NORMAL);
		//删除状态描述
		${entityCamelParamName}.setDelStatusDesc(getLanValue(DicitionaryConstants.DELETE_STATUS_NORMAL_DESC));
		//锁定状态
		${entityCamelParamName}.setLockStatus(DicitionaryConstants.USE_STATUS_NORMAL);
		//锁定状态描述
		${entityCamelParamName}.setLockStatusDesc(getLanValue(DicitionaryConstants.USE_STATUS_NORMAL_DESC));
		//设置当前语言
		${entityCamelParamName}.setLanguageCode(getCurrentLanguage());
		
		//新增信息
		int count = ${entityParamName}Mapper.add${entityName}(${entityCamelParamName});
		
		//新增成功
		if(count == 1){
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_SUCCESS_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_SUCCESS_MSG));
		//新增失败
		}else{
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_ADD_FAILED_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_ADD_FAILED_MSG));
		}
		//返回结果对象
		return baseWebReturn;
	}
	
	/**
	 * 修改${remark!}
	 * @param info
	 */
	@Override
	public BaseWebReturn modify${entityName}(${entityCamelName} ${entityCamelParamName}){
		//定义返回结果对象
		BaseWebReturn baseWebReturn = new BaseWebReturn();
		//获取当前操作的用户
		GmUserInfoEntity gmUserInfoEntity = getCurrentUserInfo();
		if(null != gmUserInfoEntity){
			//修改用户编号
			${entityCamelParamName}.setUpdateUser(gmUserInfoEntity.getUserCode());
			//修改用户名称
			${entityCamelParamName}.setUpdateUserDesc(gmUserInfoEntity.getUserName());
		}
		//修改时间
		${entityCamelParamName}.setUpdateDate(new Date());
		
		//修改信息
		int count = ${entityParamName}Mapper.modify${entityName}(${entityCamelParamName});
		
		//修改成功
		if(count == 1){
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_SUCCESS_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_SUCCESS_MSG));
		//修改失败
		}else{
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_MODIFY_FAILED_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_MODIFY_FAILED_MSG));
		}
		//返回结果对象
		return baseWebReturn;
	}
	
	/**
	 * 修改${remark!}状态
	 * @param info
	 */
	@Override
	public BaseWebReturn modify${entityName}Status(${entityCamelName} ${entityCamelParamName}){
		//定义返回结果对象
		BaseWebReturn baseWebReturn = new BaseWebReturn();
		//获取当前状态
		String lockStatus = ${entityCamelParamName}.getLockStatus();
		//获取当前的语言
		String langeuageCode = getCurrentLanguage();
		//获取当前操作的用户
		GmUserInfoEntity gmUserInfoEntity = getCurrentUserInfo();
		if(null != gmUserInfoEntity){
			//修改用户编号
			${entityCamelParamName}.setUpdateUser(gmUserInfoEntity.getUserCode());
			//修改用户名称
			${entityCamelParamName}.setUpdateUserDesc(gmUserInfoEntity.getUserName());
		}
		//修改时间
		${entityCamelParamName}.setUpdateDate(new Date());
		//如果当前是锁定状态,修改后为正常状态
		if(DicitionaryConstants.USE_STATUS_LOCK.equals(lockStatus)){
			//正常状态
			${entityCamelParamName}.setLockStatus(DicitionaryConstants.USE_STATUS_NORMAL);
			//正常状态描述
			${entityCamelParamName}.setLockStatusDesc(LanguageUtil.getLanValue(langeuageCode, DicitionaryConstants.USE_STATUS_NORMAL_DESC));
		}else if(DicitionaryConstants.USE_STATUS_NORMAL.equals(lockStatus)){
			//锁定状态
			${entityCamelParamName}.setLockStatus(DicitionaryConstants.USE_STATUS_LOCK);
			//锁定状态描述
			${entityCamelParamName}.setLockStatusDesc(LanguageUtil.getLanValue(langeuageCode, DicitionaryConstants.USE_STATUS_LOCK_DESC));
		}
		
		//修改状态
		int count = ${entityParamName}Mapper.modify${entityName}Status(${entityCamelParamName});
		
		//修改成功
		if(count == 1){
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_SUCCESS_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_SUCCESS_MSG));
		//修改失败
		}else{
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_MODIFY_STATUS_FAILED_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_MODIFY_STATUS_FAILED_MSG));
		}
		//返回结果对象
		return baseWebReturn;
	}

	/**
	 * 删除${remark!}
	 * @param info
	 */
	@Override
	public BaseWebReturn delete${entityName}(${entityCamelName} ${entityCamelParamName}){
		//定义返回结果对象
		BaseWebReturn baseWebReturn = new BaseWebReturn();
		//获取当前的语言
		String langeuageCode = getCurrentLanguage();
		//设定为该记录为删除状态
		${entityCamelParamName}.setDelStatus(DicitionaryConstants.DELETE_STATUS_DELETE);
		//删除状态描述
		${entityCamelParamName}.setDelStatusDesc(LanguageUtil.getLanValue(langeuageCode, DicitionaryConstants.DELETE_STATUS_DELETE_DESC));
		//获取当前操作的用户
		GmUserInfoEntity gmUserInfoEntity = getCurrentUserInfo();
		if(null != gmUserInfoEntity){
			//修改用户编号
			${entityCamelParamName}.setUpdateUser(gmUserInfoEntity.getUserCode());
			//修改用户名称
			${entityCamelParamName}.setUpdateUserDesc(gmUserInfoEntity.getUserName());
		}
		//修改时间
		${entityCamelParamName}.setUpdateDate(new Date());
		
		
		//删除信息
		int count = ${entityParamName}Mapper.delete${entityName}(${entityCamelParamName});
		
		//删除成功
		if(count == 1){
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_SUCCESS_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_SUCCESS_MSG));
		//删除失败
		}else{
			baseWebReturn.setRetVal(ServiceResultConstants.RESULT_CODE.OPER_DEL_FAILED_CODE);
			baseWebReturn.setMessage(getLanValue(ServiceResultConstants.RESULT_MSG.OPER_DEL_FAILED_MSG));
		}
		//返回结果对象
		return baseWebReturn;
	}
}
