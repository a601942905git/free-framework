<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${baseCorePackage}.${moduleName!}.${daoPackage}.${entityName!}Mapper">
	<sql id="BASE_SELECT_COLUMN">
    	<#list columns as col>
		${col.columnName}<#if col_index lt columns?size-1>,</#if>
    	</#list>
  	</sql>
  	
  	<select id="get${entityName}" resultType="${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName}" parameterType="${primaryPropertyType}">
  		SELECT <include refid="BASE_SELECT_COLUMN"/> FROM ${tableFullName} WHERE ${primaryKey!}=${'#'}{${primaryProperty!}} LIMIT 1
  	</select>
  
  	<select id="list${entityName}" resultType="${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName}" parameterType="${basePackage}.${baseCorePackage}.${moduleName}.${actionPackage}.param.${entityName}Param">
  		SELECT <include refid="BASE_SELECT_COLUMN"/> FROM ${tableFullName} WHERE 1=1
  		<#list columns as col>
  		<if test="${col.propertyName}!=null and ${col.propertyName}!=''">
  			AND ${col.columnName} = ${'#'}{${col.propertyName}}
  		</if>
    	</#list>
  	</select>
  
  	<insert id="save${entityName}" parameterType="${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName}">
  		INSERT INTO ${tableFullName} 
  		(
		  	<#list columns as col>
			<#if !col.primaryKey>${col.columnName}</#if><#if !col.primaryKey && col_index lt columns?size-1>,</#if>
		  	</#list>
		) 
  		VALUES 
  		(
  			<#list columns as col>
  			<#if !col.primaryKey>
			${'#'}{${col.propertyName}}<#if col_index lt columns?size-1>,</#if>
  			</#if>
  			</#list>
  		)
	</insert>
  
  	<update id="update${entityName}" parameterType="${basePackage}.${baseCorePackage}.${moduleName}.${entityPackage}.${entityCamelName}">
  		UPDATE ${tableFullName} SET 
  		<trim suffixOverrides=",">
	  		<#list columns as col><#assign jdbcType=col.columnType?replace(" UNSIGNED","")>
	    	<#if jdbcType=="INT">
	    	<#assign jdbcType="INTEGER">
	    	<#elseif jdbcType=="DATETIME">
	    	<#assign jdbcType="DATE">
	    	</#if>
	    	<#if jdbcType=="CHAR" || jdbcType=="VARCHAR" || jdbcType=="TEXT" || jdbcType=="BINARY" || jdbcType=="VARBINARY" || jdbcType=="BLOB">
	    		<if test="null != ${col.propertyName} and ${col.propertyName} != ''">
					${col.columnName}=${'#'}{${col.propertyName}},
				</if>
				<#else>
				<if test="null != ${col.propertyName}">
					${col.columnName}=${'#'}{${col.propertyName}},
				</if>
	    	</#if>
	  		</#list>
  		</trim>
  		WHERE ${primaryKey!}=${'#'}{${primaryProperty!}}
  	</update>
</mapper>