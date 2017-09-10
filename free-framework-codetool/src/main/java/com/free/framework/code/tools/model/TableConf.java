package com.free.framework.code.tools.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
/**
 * 模块中表的定义
 * @author mars.liu
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TableConf {

	/**
	 * 表名
	 */
	private String name;

	/**
	 * 表前缀
	 */
	private String prefix;

	/**
	 * 配置的实体类名
	 */
	private String entityName;

	/**
	 * 如果是主从表，则从表需设置该属性，表示父表的关联属性
	 */
	private String parentField;

	/**
	 * 表关联类型，分为OneToOne,OneToMany等
	 */
	private String refType;
	
	private List<TableConf> subTables = new ArrayList<TableConf>();//子表列表，即一对多的子表

}
