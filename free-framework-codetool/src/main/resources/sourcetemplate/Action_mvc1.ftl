package ${basePackage}.${baseCorePackage}.${moduleName}.${actionPackage};


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ${basePackage}.${baseCorePackage}.${moduleName}.${actionPackage}.param.${entityName}Param;
import ${basePackage}.${baseCorePackage}.${moduleName!}.${entityPackage}.${entityName!};
import ${basePackage}.${baseCorePackage}.${moduleName}.${servicePackage}.${entityName}Service;

import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.constant.StatusEnum;

import java.util.Date;


/**
 * ${remark!}
 *
 */
@Controller
@RequestMapping(${entityName}ControllerMappingURL.${entityName?upper_case}_CONTROLLER)
public class ${entityName}Controller extends BaseController {
	
	@Autowired
	private ${entityName}Service ${entityParamName}Service;

	/**
	 * 查询${remark!}列表信息
	 * @param
	 * @return
	 */
    @GetMapping(${entityName}ControllerMappingURL.${entityName?upper_case})
	public String list${entityName}List(${entityName!}Param ${entityParamName}Param){
		PageInfo pageInfo = ${entityParamName}Service.page${entityName!}(${entityParamName}Param);
		setRequestAttribute("pageInfo", pageInfo);
		setRequestAttribute("${entityParamName}Param", ${entityParamName}Param);
		return ${entityName!}ControllerMappingURL.PAGE_LIST_RETURN;
	}

	/**
	* 跳转新增页面
	* @param
	* @return
	*/
	@GetMapping(${entityName!}ControllerMappingURL.PAGE_ADD)
	public String addPage(){
		return ${entityName!}ControllerMappingURL.PAGE_ADD_RETURN;
	}

	/**
	* 跳转修改页面
	* @param ${primaryProperty}
	* @return
	*/
	@GetMapping(${entityName!}ControllerMappingURL.PAGE_UPDATE)
	public String updatePage(Integer ${primaryProperty}){
		${entityName!} ${entityParamName} = ${entityParamName}Service.get${entityName!}(${primaryProperty});
		setRequestAttribute("${entityParamName}", ${entityParamName});
		return ${entityName!}ControllerMappingURL.PAGE_UPDATE_RETURN;

	}
	
	/**
	 * 查询${remark!}详情信息
	 * @param ${primaryProperty}
	 * @return
	 */
	@GetMapping(${entityName}ControllerMappingURL.ONE_${entityName?upper_case})
	public String get${entityName}Detail(@PathVariable("${primaryProperty}") ${primaryPropertyType} ${primaryProperty}){
		${entityName} ${entityParamName} = ${entityParamName}Service.get${entityName}(${primaryProperty});
		setRequestAttribute("${entityParamName}", ${entityParamName});
		return ${entityName}ControllerMappingURL.PAGE_DETAIL_RETURN;
	}

	/**
	 * 新增保存${remark!}
	 * @param ${entityParamName}
	 */
	@PostMapping(${entityName}ControllerMappingURL.${entityName?upper_case})
	@ResponseBody
	public ResponseData save${entityName}(${entityName} ${entityParamName}){
		ResponseData responseData = ${entityParamName}Service.save${entityName}(${entityParamName});
		return responseData;
	}
	
	/**
	 * 修改${remark!}
	 * @param ${entityParamName}
	 */
	@PutMapping(${entityName}ControllerMappingURL.${entityName?upper_case})
	@ResponseBody
	public ResponseData update${entityName}(${entityName} ${entityParamName}){
		ResponseData responseData = ${entityParamName}Service.update${entityName}(${entityParamName});
		return responseData;
	}
}
