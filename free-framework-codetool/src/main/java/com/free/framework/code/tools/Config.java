package com.free.framework.code.tools;

import com.free.framework.code.tools.model.Db;
import com.free.framework.code.tools.model.Module;
import com.free.framework.code.tools.model.PackageSetting;
import com.free.framework.code.tools.model.TableConf;
import com.free.framework.code.tools.param.GenerateCodeParam;
import com.free.framework.code.tools.utils.XmlUtil;
import lombok.*;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;




/**
 * 读取配置文件
 * @author mars.liu
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Config {

	/**
	 * 文件存放默认路径  比如D:/
	 */
	private String baseDir;

	/**
	 * 基础项目报名 比如:com.dremoon.plateform
	 */
	private String basePackage;

	/**
	 * 业务包
	 */
	private String baseCorePackage;


	/**
	 * web包
	 */
	private String baseWebPackage;

	/**
	 * 连接数据库的配置信息
	 */
	private Db db;

	/**
	 * 要生成的代码模块列表
	 */
	private List<Module> modules;

	/**
	 * 全局包名设置
	 */
	private PackageSetting packageSetting;

	/**
	 * 需要过滤的表
	 */
	private String excludeTables;

	/**
	 * 需要过滤的列
	 */
	private String excludeColumns;

	public static Config loadConfig(GenerateCodeParam generateCodeParam){
		Config cfg = new Config();
		
		/*******************************************基础包设置开始******************************************************/
		//生成代码存放的目录路径
		cfg.setBaseDir(generateCodeParam.getBaseDir());
		
		//项目的基础包 如com.dremoon.framework
		cfg.setBasePackage(generateCodeParam.getBasePackage());
		
		//项目的业务包,如果没有,则直接在项目的基础包后面添加模块包名称
		cfg.setBaseCorePackage(generateCodeParam.getBaseCorePackage());
		
		//项目的web包,如果没有,则直接在项目的基础包后面添加模块包名称
		cfg.setBaseWebPackage(generateCodeParam.getBaseWebPackage());
		/*******************************************基础包设置结束******************************************************/
		
		/*******************************************实例化DB信息开始******************************************************/
		Db db = new Db();
		//设置数据库类型
		db.setDbType(generateCodeParam.getDbType());
		//设置数据库驱动
		db.setDriver(generateCodeParam.getDbDriver());
		//设置数据库的url
		db.setUrl(generateCodeParam.getDbUrl());
		//设置数据库的账号
		db.setUser(generateCodeParam.getDbUser());
		//设置数据库密码
		db.setPwd(generateCodeParam.getDbPwd());
		//设置数据库名称
		db.setDbName(generateCodeParam.getDbName());
		//设置数据库基本信息
		cfg.setDb(db);
		/*******************************************实例化DB信息开始******************************************************/
		
		/*******************************************设置项目包名开始******************************************************/
		//加载包名设置
		PackageSetting pkgSetting=new PackageSetting();
		//action配置
		pkgSetting.setActionPackage(generateCodeParam.getActionPackage());
		//实体模型
		pkgSetting.setEntityPackage(generateCodeParam.getEntityPackage());
		//mapper
		pkgSetting.setMapperPackage(generateCodeParam.getMapperPackage());
		//dao
		pkgSetting.setDaoPackage(generateCodeParam.getDaoPackage());
		//业务层
		pkgSetting.setServicePackage(generateCodeParam.getServicePackage());
		//业务层实现
		pkgSetting.setServiceImplPackage(generateCodeParam.getServiceImplPackage());
		//列表页面
		pkgSetting.setListPage(generateCodeParam.getListPage());
		//新增页面
		pkgSetting.setAddPage(generateCodeParam.getAddPage());
		//修改页面
		pkgSetting.setModifyPage(generateCodeParam.getModifyPage());
		//是否去掉数据库中表名前缀
		pkgSetting.setIsDeleteTablePrefix(generateCodeParam.getIsDeleteTablePrefix());
		//设置包信息
		cfg.setPackageSetting(pkgSetting);
		//设置需要过滤的表
		cfg.setExcludeTables(generateCodeParam.getExcludeTables());
		//设置需要过滤的列
		cfg.setExcludeColumns(generateCodeParam.getExcludeColumns());
		/*******************************************设置项目包名结束******************************************************/
		
		//加载module
		List<Module> moduleList = new ArrayList<>();
		Module module = new Module();
		module.setName("");
		module.setPersistance(generateCodeParam.getPersistanceFramework());
		module.setDeleteTablePrefix(generateCodeParam.getIsDeleteTablePrefix());
		module.setPrefix(generateCodeParam.getPrefix());
		module.setFramework(generateCodeParam.getFramework());
		module.setDaoPackage(generateCodeParam.getDaoPackage());
		module.setServicePackage(generateCodeParam.getServicePackage());
		module.setServiceImplPackage(generateCodeParam.getServiceImplPackage());
		module.setEntityPackage(generateCodeParam.getEntityPackage());
		module.setActionPackage(generateCodeParam.getActionPackage());
		module.setListPage(generateCodeParam.getListPage());
		module.setAddPage(generateCodeParam.getAddPage());
		module.setModifyPage(generateCodeParam.getModifyPage());
		module.setDetailPage(generateCodeParam.getDetailPage());
		module.setMapperPackage(generateCodeParam.getMapperPackage());
		module.setSavePath(generateCodeParam.getMapperSavePath());
		module.setFrameworkMapping(generateCodeParam.getFrameworkMapping());
		moduleList.add(module);
		cfg.setModules(moduleList);
		return cfg;
	}
	
	/**
	 * 以递归方式读取主从表关系
	 * @param module
	 * @return
	 */
	private static List<TableConf> readTableConfList(Element module){
		List<TableConf> tableList = new ArrayList<TableConf>();
		List<Element> tables = XmlUtil.getChildElements(module, "table");
		for (Element e : tables) {
			TableConf table = initTableConf(e);
			//table标签下面还可以有table标签
			List<TableConf> subTables = readTableConfList(e);
			if (subTables!=null && !subTables.isEmpty()) {
				table.getSubTables().addAll(subTables);
			}
			tableList.add(table);
		}
		return tableList;
	}
	/**
	 * 初始化配置的表格信息
	 * @param e
	 * @return
	 */
	private static TableConf initTableConf(Element e){
		TableConf table = new TableConf();
		//表对应的实体类名称
		Attribute attr = XmlUtil.getAttribute(e, "entityName");
		if (attr!=null) {
			table.setEntityName(attr.getValue());
		}
		//表名称
		Attribute name = XmlUtil.getAttribute(e, "name");
		if (name!=null) {
			table.setName(name.getValue());
		}
		//表前缀
		Attribute prefix = XmlUtil.getAttribute(e, "prefix");
		if (prefix!=null) {
			table.setPrefix(prefix.getValue());
		}
		//如果是主从表，则从表需设置该属性，表示父表的关联属性
		Attribute parentField = XmlUtil.getAttribute(e, "parentField");
		if (parentField!=null) {
			table.setParentField(parentField.getValue());
		}
		//表关联类型，分为OneToOne,OneToMany
		Attribute refType =XmlUtil.getAttribute(e, "refType");
		if (refType!=null) {
			table.setRefType(refType.getValue());
		} else {
			table.setRefType("OneToMany"); //默认OneToMany
		}
		return table;
	}
}
