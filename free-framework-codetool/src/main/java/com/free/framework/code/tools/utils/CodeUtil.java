package com.free.framework.code.tools.utils;

import java.util.List;

public class CodeUtil {
	
	/* 
     * 表名转换为驼峰命名 
     */  
    public static String convertToCamelCase(String str) {  
        String result = "";  
        System.out.println("=======转驼峰之前名称========" + str);
        String[] strArr = str.trim().split("_");
        for(String s : strArr) {
            if (s.length() > 1) {
                result += s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            } else {
                result += s.toUpperCase();
            }  
        }  
  
        return result;  
    }

    public static void main(String[] args) {
        System.out.println(convertToCamelCase("USER"));
    }
      
    /* 
     * 表名转换为首字母小写的驼峰命名 
     */  
    public static String convertToFirstLetterLowerCaseCamelCase(String str) {  
        String resultCamelCase = convertToCamelCase(str);  
  
        String result = "";  
        if(resultCamelCase.length()>1) {  
            result = resultCamelCase.substring(0, 1).toLowerCase() + resultCamelCase.substring(1);  
        } else {  
            result = resultCamelCase.toLowerCase();  
        }  
          
        return result;  
    }
    
    /* 
     * 首字母小写
     */  
    public static String firstLetterToLowerCase(String str) {
    	String result = "";
    	if(null != str && !"".equals(str)){
    		result = str.substring(0,1).toLowerCase() + str.substring(1);
    	}
        return result;  
    }
    
    /* 
     * 首字母大写
     */  
    public static String firstLetterToUpperCase(String str) {
    	String result = "";
    	if(null != str && !"".equals(str)){
    		result = str.substring(0,1).toUpperCase() + str.substring(1);
    	}
        return result;  
    }
    
    /* 
     * 将数据库的数据类型转换为java的数据类型 
     */  
    public static String convertType(String databaseType) {  
        String javaType = "";  
          
        String databaseTypeStr = databaseType.trim().toLowerCase();
        if(databaseTypeStr.startsWith("int")
        		||databaseTypeStr.equals("smallint")
        		|| databaseTypeStr.equals("tinyint")
        		) {  
            javaType = "Integer";  
        } else if(databaseTypeStr.equals("char")) {  
            javaType = "String";  
        } else if(databaseTypeStr.equals("number") 
        		|| databaseTypeStr.equals("numeric")
        		) {  
            javaType = "Integer";  
        } else if(databaseTypeStr.indexOf("varchar")!=-1) {  
            javaType = "String";  
        } else if(databaseTypeStr.equals("blob")) {  
            javaType = "Byte[]";  
        } else if(databaseTypeStr.equals("float")) {  
            javaType = "Float";  
        } else if(databaseTypeStr.equals("double")) {  
            javaType = "Double";  
        } else if(databaseTypeStr.equals("decimal")) {  
            javaType = "java.math.BigDecimal";  
        } else if(databaseTypeStr.startsWith("bigint")) {  
            javaType = "Long";  
        } else if(databaseTypeStr.equals("date")) {  
            javaType = "java.util.Date";  
        } else if(databaseTypeStr.equals("time")) {  
            javaType = "java.util.Date";  
        } else if(databaseTypeStr.equals("datetime")) {  
            javaType = "java.util.Date";  
        } else if(databaseTypeStr.equals("timestamp")) {  
            javaType = "java.util.Date";  
        } else if(databaseTypeStr.equals("year")) {  
            javaType = "java.util.Date";  
        } else {
            javaType = "String";  
        }  
          
        return javaType;  
    }
    
    /**
     * 转换jdbc的类型，主要用于mybatis中的数据字段类型
     * @param type
     * @return
     */
    public static String convertJdbcType(String type) {
    	type=type.replace(" UNSIGNED","");
    	if (type.equals("INT")) {
        	type="INTEGER";
        } else if (type.equals("TEXT")){
    		type="LONGVARCHAR";
    	} else if (type.equals("DATETIME")) {
    		type="DATE";
    	} else if (type.equals("VARCHAR2")) {
    		type="VARCHAR";
    	} else if (type.equals("NUMBER")) {
    		type="NUMERIC";
    	} else if (type.equals("NVARCHAR")) {
    		type="VARCHAR";
    	}
    	return type;
    }
    
    public static boolean isEmpty(String str) {
    	if (str==null) {
    		return true;
    	}
    	if (str.length()==0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean existsType(List<String> list , String type) {
    	return list.contains(type);
    }

    /**
     * <p>Descrption:根据表名称生成实体类名称 例如:表名称 gm_area_info 实体类名称为GmAreaInfoEntity</p>
     * <p>Todo: TODO </p>
     * @Author: lipeng
     * @Date: 2016年9月9日 上午11:01:26
     * @Tags: @param tableName
     * @Tags: @return
     * @Return: String
     */
	public static String getEntityCamelName(String tableName) {
		String result = convertToCamelCase(tableName);
		return result;
	}

	/**
	 * <p>Descrption:根据表名称获取模块名称</p>
	 * <p>Todo: TODO </p>
	 * @Author: lipeng
	 * @Date: 2016年9月9日 上午11:07:44
	 * @Tags: @param tableName
	 * @Tags: @return
	 * @Return: String
	 */
	public static String getModuleByTableName(String tableName) {
		//默认的模块名称
		String moduleName = tableName;
		if(tableName.indexOf("_") != -1){
			String[] tableNames = tableName.split("_");
			System.out.println("=moduleName===" + moduleName + "==tableNames==" + tableNames.length);
			//如果表名称是如下格式:gm_area
			if(tableNames.length == 2){
				moduleName = tableNames[1];
			//如果表名称是如下格式:gm_product_class
			}else if(tableNames.length ==3){
				moduleName = tableNames[1] + tableNames[2];
			}else if(tableNames.length ==4){
				moduleName = tableNames[1] + tableNames[2] + tableNames[3];
			}
		}
		return moduleName;
	}

}
