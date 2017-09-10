package com.free.framework.code.tools.model;

import lombok.*;

import java.util.List;

/**
 * 模块信息
 * @author mars.liu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Module {

	/**
	 * 模块名称
	 */
	private String name;

	/**
	 * 启用的持久层框架，选项有：hibernate,mybatis,jdbc，其中hibernate为默认
	 */
	private String persistance;

	/**
	 * 是否删除表前缀
	 */
	private boolean isDeleteTablePrefix;

	/**
	 * 前缀
	 */
	private String prefix;

	/**
	 * 默认保存目录，文件保存到该目录下，暂时不使用此属性值
	 */
	private String savePath;

	/**
	 * 使用前端框架，可设置为dorado和mvc，mvc表示使用spring-mvc
	 */
	private String framework;

	/**
	 * 前端控制器请求映射路径
	 */
	private String frameworkMapping;

	/**
	 * dao
	 */
	private String daoPackage;

	/**
	 * daoImpl
	 */
	private String daoImplPackage;

	/**
	 * service
	 */
	private String servicePackage;

	/**
	 * serviceImpl
	 */
	private String serviceImplPackage;

	/**
	 * entity
	 */
	private String entityPackage;

	/**
	 * action
	 */
	private String actionPackage;

	/**
	 * view
	 */
	private String viewPackage;

	/**
	 * listPage
	 */
	private String listPage;

	/**
	 * detailPage
	 */
	private String detailPage;

	/**
	 * addPage
	 */
	private String addPage;

	/**
	 * modifyPage
	 */
	private String modifyPage;

	/**
	 * mapperPackage
	 */
	private String mapperPackage;

	/**
	 * mybatisPackage
	 */
	private String myBatisPackage;

	/**
	 * 配置的数据表信息
	 */
	private List<TableConf> tables;
}
