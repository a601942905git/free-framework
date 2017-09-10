package com.free.framework.code.tools.dbservice.impl;

import com.free.framework.code.tools.Config;
import com.free.framework.code.tools.dbservice.ITableService;
import com.free.framework.code.tools.model.Column;
import com.free.framework.code.tools.model.Module;
import com.free.framework.code.tools.model.Table;
import com.free.framework.code.tools.model.TableConf;
import com.free.framework.code.tools.utils.CodeUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlTableService implements ITableService {
	
	private Config config;
	public void setConfig(Config config) {
		this.config = config;
	}

	/* 
     * 连接数据库获取所有表信息 
     */  
    public List<TableConf> getAllTables(String pattern) {
        if (CodeUtil.isEmpty(pattern)) {
        	pattern="*";
        }
        List<TableConf> tbConfList = new ArrayList<TableConf>();
        Connection con = null;  
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {  
            Class.forName(config.getDb().getDriver());  
            con = DriverManager.getConnection(config.getDb().getUrl(), config.getDb().getUser(),config.getDb().getPwd());  
            // 获取所有表名  
            String showTablesSql = "";  
            showTablesSql = "show tables like '"+pattern+"'";  // MySQL查询所有表格名称命令  
            ps = con.prepareStatement(showTablesSql);  
            rs = ps.executeQuery();  
              
            // 循环生成所有表的表信息
            while(rs.next()) {  
                if(rs.getString(1)==null) continue; 
                System.out.println("config.getExcludeTables() +======" + config.getExcludeTables() + "====" + rs.getString(1));
				String excludeTables = config.getExcludeTables();
				//过滤掉系统默认的表
                if(StringUtils.isNotEmpty(excludeTables) && excludeTables.indexOf(rs.getString(1) + "&") == -1){
                	continue;
                }
				TableConf cf = new TableConf();
				cf.setName(rs.getString(1));
				tbConfList.add(cf);
            }  
              
            rs.close();  
            ps.close(); 
            con.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return tbConfList;  
    }  
      
    /**
     * 获取指定表信息并封装成Table对象 
     * @param tbConf 
     * @param module
     * @param con 
     */  
    public Table getTable(TableConf tbConf, Module module, Connection con) throws SQLException {
		String tableName = tbConf.getName();
		Table table = new Table();
        table.setTableFullName(tableName);
        table.setTableName(tableName);
        //如果设置了表的前缀,则去掉表的前缀
        if (module.isDeleteTablePrefix() && !CodeUtil.isEmpty(tbConf.getPrefix())){
        	table.setTableName(tableName.toLowerCase().replaceFirst(tbConf.getPrefix(), ""));  
        }
        System.out.println("==表信息==="+tbConf);
        //设置模块名称
        module.setName(CodeUtil.getModuleByTableName(tableName));
        //获取表各字段的信息
        getTableColumns(table,con);
        table.setPrimaryKey(getTablePrimaryKey(tableName, con));
        System.out.println("========table.getPrimaryKey()========" + table.getPrimaryKey() + "=====" + tableName);
        table.setPrimaryProperty(CodeUtil.convertToFirstLetterLowerCaseCamelCase(table.getPrimaryKey())); 
        table.setRemark(getTableRemark(tableName, con));
        table.setPrimaryKeyType(getColumnType(table, table.getPrimaryKey()));
        table.setPrimaryPropertyType(CodeUtil.convertType(table.getPrimaryKeyType()));
        table.setPrimaryCamelProperty(CodeUtil.convertToCamelCase(table.getPrimaryKey()));
        //根据表名获取实体类名称 如:表名称为free_user去掉前缀之后为user,转换之后为User
        table.setEntityCamelName(CodeUtil.convertToCamelCase(table.getTableName()));
        //根据表名获取实体类名称参数 如:User转换之后为user
        table.setEntityCamelParamName(CodeUtil.firstLetterToLowerCase(table.getEntityCamelName()));
        /***********************************此处原本的处理方式是将首字母小写,由于不满足,所以改成了首字母大写************************************/
        table.setEntityName(CodeUtil.convertToCamelCase(table.getTableName()));
        table.setEntityParamName(CodeUtil.firstLetterToLowerCase(table.getEntityName()));
        table.setModule(module);
        //设置子表的entity属性
        if (!tbConf.getSubTables().isEmpty()) {
        	List<Table> subTables = new ArrayList<Table>();
        	for (TableConf tc : tbConf.getSubTables()) {
        		Table tb = getTable(tc,module,con);
        		tb.setParentProperty(CodeUtil.convertToFirstLetterLowerCaseCamelCase(tc.getParentField()));
        		tb.setRefType(tc.getRefType());
        		subTables.add(tb);
        	}
        	table.setSubTables(subTables);
        }
        return table;  
    } 
    
    /**
     * 获取数据表的所有字段
     * @param table
     * @param conn
     * @throws SQLException
     */
    public void getTableColumns(Table table,Connection conn) throws SQLException {
    	if (config.getDb().getDbType().equals("mysql")) {
			String sql="select * from information_schema.COLUMNS where TABLE_SCHEMA=? and TABLE_NAME=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,config.getDb().getDbName());
			ps.setString(2,table.getTableFullName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Column col = new Column();
	        	String colName = rs.getString("column_name");
        		col.setColumnName(colName);
	        	String type = rs.getString("data_type").toUpperCase();
	        	type=CodeUtil.convertJdbcType(type);
	        	col.setColumnType(type);
	        	col.setRemark(rs.getString("column_comment"));
	        	col.setPropertyName(CodeUtil.convertToFirstLetterLowerCaseCamelCase(colName));
        		col.setPropertyType(CodeUtil.convertType(col.getColumnType()));
	        	col.setPropertyCamelName(CodeUtil.convertToCamelCase(colName));
	        	col.setNullable(rs.getString("is_nullable").equals("YES"));
	        	col.setLength(rs.getLong("character_maximum_length"));
	        	col.setDefaultValue(rs.getString("column_default"));
	        	
	        	String colKey = rs.getString("column_key");
	        	if (!CodeUtil.isEmpty(colKey) && colKey.toLowerCase().equals("pri")) {
	        		col.setPrimaryKey(true);
	        	}
	        	if (col.getPropertyType().indexOf(".")!=-1 && !CodeUtil.existsType(table.getImportClassList(),col.getPropertyType())) {
	        		table.getImportClassList().add(col.getPropertyType());
	        	}
	        	table.getColumns().add(col);
			}
			rs.close();
			ps.close();
		} else { //其它数据库
			
		}
    }
    
    public String getTablePrimaryKey(String tableName, Connection con) throws SQLException{
		DatabaseMetaData dbMeta = con.getMetaData(); 
		ResultSet rs = dbMeta.getPrimaryKeys(null,null,tableName);
		if (rs.next()){
			return (rs.getString("COLUMN_NAME"));
		}
		return null;
	}
	/**
	 * 主键类型
	 * @param table
	 * @param column 指定列名
	 * @return
	 * @throws SQLException
	 */
	public String getColumnType(Table table,String column) throws SQLException{
		String colType="";
		for (Column col : table.getColumns()) {
			if (col.getColumnName().equalsIgnoreCase(column)) {
				return col.getColumnType();
			}
		}
		return colType;
	}
	/**
	 * 表注释
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public String getTableRemark(String tableName, Connection con) throws SQLException {
		String remark="";
		String sql="show table status where name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tableName);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			remark=rs.getString("comment");
		}
		rs.close();
		ps.close();
		return remark;
	}

}
