package com.free.framework.code.tools.model;

import lombok.*;

/**
 * 定义全局表名设置
 * @author mars.liu
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PackageSetting {
	private String daoPackage;
	private String daoImplPackage;
	private String servicePackage;
	private String serviceImplPackage;
	private String entityPackage;
	private String mapperPackage;
	private String actionPackage;
	private String viewPackage;
	private String listPage;
	private String detailPage;
	private String addPage;
	private String modifyPage;
	private Boolean isDeleteTablePrefix;
}
