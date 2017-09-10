package com.free.framework.code.tools.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Column {

	/**
	 * 列名称
	 */
	private String columnName;

	/**
	 * 列类型
	 */
    private String columnType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 实体类属性名称
	 */
    private String propertyName;

	/**
	 * 实体类属性名称
	 */
	private String propertyType;

	/**
	 * 首字母大写属性名
	 */
    private String propertyCamelName;

	/**
	 * 是否主键
	 */
	private boolean isPrimaryKey;

	/**
	 * 是否允许为空
	 */
    private boolean isNullable;

	/**
	 * 字段长度
	 */
    private Long length;

	/**
	 * 字段默认值
	 */
	private Object defaultValue;
}
