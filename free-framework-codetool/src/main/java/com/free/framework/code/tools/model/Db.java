package com.free.framework.code.tools.model;

import lombok.*;

/**
 * 数据库连接信息
 * @author mars.liu
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Db {

	/**
	 * 数据库类型
	 */
	private String dbType;

	/**
	 * 用户名
	 */
	private String user;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 驱动
	 */
	private String driver;

	/**
	 * 连接url地址
	 */
	private String url;

	/**
	 * 数据库名称
	 */
	private String dbName;
}
