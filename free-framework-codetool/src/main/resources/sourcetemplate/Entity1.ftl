package ${basePackage}.${baseCorePackage}.${moduleName!}.${entityPackage};

import java.io.Serializable;
import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;
<#if subTables?size gt 0>
import java.util.List;
</#if>
<#if module.persistance=="hibernate" || module.persistance=="jpa">
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
<#if subTables?size gt 0>
import javax.persistence.Transient;
</#if>
</#if>
<#if importClassList??>
<#list importClassList as imp>
import ${imp!};
</#list>
</#if>

/**
 * ${remark!}
 */
<#if module.persistance=="hibernate" || module.persistance=="jpa">
@Entity
@Table(name="${tableFullName!}")
</#if>
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ${entityName!} extends BaseEntity implements Serializable {

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
