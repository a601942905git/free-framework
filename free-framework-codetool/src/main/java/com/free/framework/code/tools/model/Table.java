package com.free.framework.code.tools.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Table {
	/**
	 * 模块名称
	 */
	private Module module;
	private String packageName;
	/**
	 * 完整表名
	 */
	private String tableFullName;

	/**
	 * 表名，去掉prefix
	 */
	private String tableName;

	/**
	 * 实体名--->对应表名Company
	 */
	private String entityName;

	/***
	 * 实体名参数名(company)
	 */
	private String entityParamName;

	/**
	 * 完整实体类名---->xml中配的实体名称(Company)
	 */
	private String entityCamelName;

	/**
	 * 完整实体参数名称(company)
	 */
	private String entityCamelParamName;

	/**
	 * 表注释
	 */
	private String remark;

	/**
	 * 主键
	 */
	private String primaryKey;

	/**
	 * 主键类型
	 */
	private String primaryKeyType;

	/**
	 * 主键属性名
	 */
	private String primaryProperty;

	/**
	 * 主键属性类型
	 */
	private String primaryPropertyType;

	/**
	 * 主键属性
	 */
	private String primaryCamelProperty;

	/**
	 * 若为子表，则此属性为上级表的属性
	 */
	private String parentProperty;

	/**
	 * 表间关联类型
	 */
	private String refType;

	private List<String> importClassList = new ArrayList<String>();
	private List<Column> columns = new ArrayList<Column>();
	private List<Table> subTables = new ArrayList<Table>();
}