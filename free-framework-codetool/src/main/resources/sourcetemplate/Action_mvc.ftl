package ${basePackage}.${baseWebPackage}.${moduleName}.${actionPackage};

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${basePackage}.${baseWebPackage}.${moduleName}.${actionPackage}.${moduleName?cap_first}ControllerMappingURL;
import com.dremoon.framework.common.webmanager.constants.Constants;
import com.dremoon.framework.common.webmanager.action.DremoonWebAction;
import com.dremoon.framework.common.webmanager.vo.returns.BaseWebReturn;
import ${basePackage}.${baseCorePackage}.${moduleName}.${servicePackage}.${entityName}Service;
import ${basePackage}.${baseCorePackage}.${moduleName!}.${entityPackage}.${entityCamelName!};
import com.dremoon.plateform.core.job.entity.GmUserJobEntity;
import com.dremoon.plateform.core.webuser.entity.GmUserInfoEntity;


/**
 * ${remark!}
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(${moduleName?cap_first}ControllerMappingURL.${moduleName?upper_case}_CONTROLLER)
@Scope(Constants.WEB_REQUEST_TYPE)
public class ${moduleName?cap_first}Controller extends DremoonWebAction<GmUserInfoEntity,GmUserJobEntity> {
	
	@Autowired
	private ${entityName}Service ${entityParamName}Service;

	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.${moduleName?upper_case}_CONTROLLER_EXECUTE)
	public String execute(){
		//返回到信息管理页面
		return ${moduleName?cap_first}ControllerMappingURL.${moduleName?upper_case}_CONTROLLER_EXECUTE_RETURN;
	}

	/**
	 * 查询${remark!}列表信息
	 * @param
	 * @return
	 */
	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.GET_${moduleName?upper_case}_LIST)
	public void get${entityName}List(HttpServletResponse response){
		//查询公司列表信息
		List<${entityCamelName}> list = ${entityParamName}Service.get${entityName}List();
		//返回查询结果数据
		writeResponseDataNoKey(response,list);
	}
	
	/**
	 * 查询${remark!}详情信息
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.GET_${moduleName?upper_case}_DETAIL)
	public void get${entityName}Detail(HttpServletResponse response,${primaryPropertyType} ${primaryProperty}){
		${entityCamelName} ${entityCamelParamName} = ${entityParamName}Service.get${entityName}Detail(id);
		//返回查询结果数据
		writeResponseDataNoKey(response,${entityCamelParamName});
	}

	/**
	 * 新增保存${remark!}
	 * @param info
	 */
	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.ADD_${moduleName?upper_case})
	public void add${entityName}(HttpServletResponse response,${entityCamelName} ${entityCamelParamName}){
		BaseWebReturn baseWebReturn = ${entityParamName}Service.add${entityName}(${entityCamelParamName});
		writeResponseDataNoKey(response,baseWebReturn);
	}
	
	/**
	 * 修改${remark!}
	 * @param info
	 */
	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.MODIFY_${moduleName?upper_case})
	public void modify${entityName}(HttpServletResponse response,${entityCamelName} ${entityCamelParamName}){
		BaseWebReturn baseWebReturn = ${entityParamName}Service.modify${entityName}(${entityCamelParamName});
		writeResponseDataNoKey(response,baseWebReturn);
	}
	
	/**
	 * 修改${remark!}状态
	 * @param info
	 */
	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.MODIFY_${moduleName?upper_case}_STATUS)
	public void modify${entityName}Status(HttpServletResponse response,${entityCamelName} ${entityCamelParamName}){
		BaseWebReturn baseWebReturn = ${entityParamName}Service.modify${entityName}Status(${entityCamelParamName});
		writeResponseDataNoKey(response,baseWebReturn);
	}

	/**
	 * 删除${remark!}
	 * @param info
	 */
	@RequestMapping(value=${moduleName?cap_first}ControllerMappingURL.DELETE_${moduleName?upper_case})
	public void delete${entityName}(HttpServletResponse response,${entityCamelName} ${entityCamelParamName}){
		BaseWebReturn baseWebReturn = ${entityParamName}Service. delete${entityName}(${entityCamelParamName});
		writeResponseDataNoKey(response,baseWebReturn);
	}
}
