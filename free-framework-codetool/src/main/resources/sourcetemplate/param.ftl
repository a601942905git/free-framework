package ${basePackage}.${baseCorePackage}.${moduleName!}.${actionPackage}.param.${entityName!}Param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;

@Data
public class ${entityName!}Param extends BaseParam {

<#if columns??>
	<#list columns as col>
	/**
	 * ${col.remark!}
	 */
	<#assign type=col.propertyType>
	<#assign type=type?replace("java.util.","")>
	<#assign type=type?replace("java.math.","")>
	<#assign defaultValue=col.defaultValue!>
	<#if defaultValue?length gt 0>
		<#if type=="Date">
			<#assign defaultValue="new Date()">
		<#elseif type=="Long">
			<#assign defaultValue=defaultValue+"L">
		<#elseif type=="Double">
			<#assign defaultValue=defaultValue+"d">
		</#if>
	</#if>

	private ${type!} ${col.propertyName}${(defaultValue?length>0)?string("="+defaultValue,"")};

	</#list>
</#if>
}
