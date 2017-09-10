package com.free.framework.code.tools.param;

import lombok.*;

/**
 * 代码生成参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GenerateCodeParam {

	/**
	 * 文件存放路径
	 */
	private String baseDir = "";

	/**
	 * 项目默认的包名称
	 */
	private String basePackage = "";

	/**
	 * 业务包
	 */
	private String baseCorePackage = "";

	/**
	 * web包
	 */
	private String baseWebPackage = "";

	/**
	 * 数据库类型,默认mysql
	 */
	private String dbType = "";

	/**
	 * 数据库用户名
	 */
	private String dbUser;

	/**
	 * 密码
	 */
	private String dbPwd;

	/**
	 * 驱动
	 */
	private String dbDriver;

	/**
	 * 数据库连接url
	 */
	private String dbUrl;

	/**
	 * 数据库名称
	 */
	private String dbName;

	/**
	 * dao包名称
	 */
	private String daoPackage;

	/**
	 * 业务包名称
	 */
	private String servicePackage;

	/**
	 * 业务实现包名称
	 */
	private String serviceImplPackage;

	/**
	 * 实体类包名称
	 */
	private String entityPackage;

	/**
	 * mapper包名称
	 */
	private String mapperPackage;

	/**
	 * mapper.xml保存路径
	 */
	private String mapperSavePath;

	/**
	 * action包名称
	 */
	private String actionPackage;

	/**
	 * 列表页后缀名称
	 */
	private String listPage;

	/**
	 * 新增页面后缀名称
	 */
	private String addPage;

	/**
	 * 修改页面后缀名称
	 */
	private String modifyPage;

	/**
	 * 详情页
	 */
	private String detailPage;

	/**
	 * 是否删除数据库表的前缀 如:gm
	 */
	private Boolean isDeleteTablePrefix;

	/**
	 * 前缀
	 */
	private String prefix;

	/**
	 * 持久层框架
	 */
	private String persistanceFramework;

	/**
	 * 前端框架 使用前端框架，可设置为dorado和mvc，mvc表示使用spring-mvc
	 */
	private String framework;

	/**
	 * 前端控制器请求映射路径类
	 */
	private String frameworkMapping;

	/**
	 * 需要过滤的表
	 */
	private String excludeTables;

	/**
	 * 需要过滤的列
	 */
	private String excludeColumns;

}
