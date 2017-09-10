package com.free.framework.code.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.free.framework.code.tools.dbservice.ITableService;
import com.free.framework.code.tools.model.Module;
import com.free.framework.code.tools.model.Table;
import com.free.framework.code.tools.model.TableConf;
import com.free.framework.code.tools.param.GenerateCodeParam;
import com.free.framework.code.tools.utils.CodeUtil;
import com.free.framework.code.tools.utils.FreemarkerUtil;
import com.free.framework.code.tools.utils.ZipUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 通过数据库表结构生成表对应的实体类以及根据entity,view,action、service和dao模板生成基本的相关文件，支持深度主从表关系<br>
 * 后期可考虑抽像出一些接口来实现更多的持久层、展现层框架
 *
 * @author mars.liu
 */
public class DataBase2File {

    private Config config = null;

    public DataBase2File(GenerateCodeParam generateCodeParam) {
        config = Config.loadConfig(generateCodeParam);
    }

    private ITableService tableService;

    /**
     * 主方法，反转生成相关文件
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void generateFiles() throws IOException, ClassNotFoundException, SQLException {
        System.out.println("Generating...");
        Long start = System.currentTimeMillis();
        //根据数据库类型获取数据库实例
        tableService = TableServiceFactory.getInstance(config.getDb().getDbType());
        //设置数据库实例的配置信息
        tableService.setConfig(config);
        //加载数据库驱动
        Class.forName(config.getDb().getDriver());
        //连接数据库
        Connection con = DriverManager.getConnection(config.getDb().getUrl(), config.getDb().getUser(), config.getDb().getPwd());
        //加载模块信息
        for (Module module : config.getModules()) {
            System.out.println("module=" + module.getName());
            //如果没有配置数据表，则默认加载模块名为前缀的所有数据表
            if (module.getTables() == null || module.getTables().isEmpty()) {
                //获取对应数据库中所有的表
                module.setTables(tableService.getAllTables(module.getName() + "%"));
            }

            //系统的model文件
            StringBuffer sbModel = new StringBuffer();
            if (module.getTables() != null) {
                for (TableConf tbConf : module.getTables()) {
                    tbConf.setPrefix(module.getPrefix());
                    //获取一个表的相关信息同时设置模块名称
                    Table table = tableService.getTable(tbConf, module, con);
                    genFile(table, module);
                }
            }
            //System.out.println(sbModel.toString());
            if ("dorado".equals(module.getFramework())) {
                writeModelFile(module.getName().split("/")[0] + ".model.xml", sbModel.toString());//生成model文件
            }
        }
        con.close();
        ZipUtil.zip(config.getBaseDir(), config.getBaseDir() + ".zip");
        Long end = System.currentTimeMillis();
        System.out.println("Generate Success! total time = " + (end - start));
        System.out.println("Please check: " + config.getBaseDir());
    }

    /**
     * 递归生成所有文件代码，子表可以重叠
     *
     * @param tb
     * @param module
     * @return
     */
    private StringBuffer genFile(Table tb, Module module) {
        generateEntityFile(tb, module);//生成entity
        generateDaoFile(tb, module);//生成dao
        generateServiceFile(tb, module);//生成service
        generateActionFile(tb, module);//生成action
        //generateViewFile(tb,module);//生成view
        generateJSPViewFile(tb, module);//生成jsp文件
        StringBuffer sb = new StringBuffer();
        //若是使用dorado框架，则生成model的数据块
        if ("dorado".equals(module.getFramework())) {
            String modleString = (generateDoradoModelString(tb, module));
            sb.append(modleString);
            if (!tb.getSubTables().isEmpty()) {
                for (Table subTb : tb.getSubTables()) {
                    sb.append(genFile(subTb, module));
                }
            }
        }
        //System.out.println(sb.toString());
        return sb;
    }


    /**
     * 将模块信息转换为json结构
     *
     * @param obj
     * @param module
     */
    private void setBaseInfo(JSONObject obj, Module module) {
        obj.put("basePackage", config.getBasePackage());
        obj.put("baseCorePackage", config.getBaseCorePackage());
        obj.put("baseWebPackage", config.getBaseWebPackage());
        obj.put("moduleName", module.getName().replace("/", "."));
        obj.put("entityPackage", module.getEntityPackage());
        obj.put("servicePackage", module.getServicePackage());
        obj.put("serviceImplPackage", module.getServiceImplPackage());
        obj.put("daoPackage", module.getDaoPackage());
        obj.put("daoImplPackage", module.getDaoImplPackage());
        obj.put("actionPackage", module.getActionPackage());
        obj.put("viewPackage", module.getViewPackage());
        obj.put("mapperPackage", module.getMapperPackage());
    }

    /**
     * 生成指定表对象对应的类文件
     *
     * @param table
     */
    private void generateEntityFile(Table table, Module module) {
        //将表的基本信息转换成json数据
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        JSONArray jsonArray = obj.getJSONArray("columns");
        if (null != jsonArray && jsonArray.size() > 0) {
            String excludeColumns = config.getExcludeColumns();
            //移除公用的实体属性信息
            for (int i = jsonArray.size(); i > 0; i--) {
                JSONObject jsonObject = jsonArray.getJSONObject(i - 1);
                if (StringUtils.isNotEmpty(excludeColumns) &&
                        excludeColumns.indexOf(jsonObject.getString("columnName") + "&") != -1) {
                    jsonArray.remove(i - 1);
                }
            }
        }
        //设置表生成代码对应的包信息
        setBaseInfo(obj, module);
        obj.put("entitySystemTime", String.valueOf(System.currentTimeMillis()));
        File saveDir = getCoreSaveFilePath(module, module.getEntityPackage());
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        File saveFile = new File(saveDir, table.getEntityName() + ".java");

        String savePath = saveFile.getAbsolutePath();
        FreemarkerUtil.createDoc(obj, "Entity1", savePath);
        System.out.println("生成文件：" + savePath);
    }

    /**
     * 根据模块定义生成文件业务文件保存目录
     *
     * @param module
     * @param packageName
     * @return
     */
    private File getCoreSaveFilePath(Module module, String packageName) {
        //模块的名称,如果存在.则替换成/
        String moduleName = module.getName().replace(".", "/");
        File saveDir;
        //文件路径E:/eclipse/workspace/TestProject/src/com.zhaiugo/模块名/包名
        if (CodeUtil.isEmpty(module.getSavePath())) {
            saveDir = new File(
                    config.getBaseDir() + File.separator +
                            config.getBasePackage().replace(".", File.separator) + File.separator +
                            config.getBaseCorePackage().replace(".", File.separator) +
                            File.separator + moduleName + File.separator + packageName.replace(".", File.separator));
        } else {
            saveDir = new File(module.getSavePath(), moduleName + File.separator + packageName);
        }
        System.out.println("========saveDir============" + saveDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveDir;
    }

    /**
     * 根据模块定义生成文件web文件保存目录
     *
     * @param module
     * @param packageName
     * @return
     */
    private File getWebSaveFilePath(Module module, String packageName) {
        //模块的名称,如果存在.则替换成/
        String moduleName = module.getName().replace(".", "/");
        File saveDir;
        //文件路径E:/eclipse/workspace/TestProject/src/com.zhaiugo/模块名/包名
        if (CodeUtil.isEmpty(module.getSavePath())) {
            saveDir = new File(
                    config.getBaseDir() + File.separator +
                            config.getBasePackage().replace(".", File.separator) + File.separator +
                            config.getBaseWebPackage().replace(".", File.separator) +
                            File.separator + moduleName + File.separator + packageName.replace(".", File.separator));
        } else {
            saveDir = new File(module.getSavePath(), moduleName + File.separator + packageName);
        }
        System.out.println("========saveDir============" + saveDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveDir;
    }

    /**
     * 生成指定表映射的RowMapper类文件，用于jdbcTemplate的查询
     *
     * @param table
     */
    private void generateMapperFile(Table table, Module module) {
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        File saveDir = getCoreSaveFilePath(module, module.getDaoPackage() + File.separator + module.getMapperPackage());
        File saveFile = new File(saveDir, table.getEntityName() + "RowMapper.java");

        String savePath = saveFile.getAbsolutePath();
        FreemarkerUtil.createDoc(obj, "RowMapper", savePath);
        System.out.println("生成文件：" + savePath);
    }

    /**
     * 生成dao文件
     *
     * @param table
     */
    private void generateDaoFile(Table table, Module module) {
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        File saveDir = getCoreSaveFilePath(module, module.getDaoPackage());
        File saveFile = new File(saveDir, table.getEntityName() + "Mapper.java");
        String savePath = saveFile.getAbsolutePath();
        String templateName = "DaoInterface";
        if ("jpa".equals(module.getPersistance())) {
            templateName = "DaoInterface_" + module.getPersistance();
        }
        FreemarkerUtil.createDoc(obj, templateName, savePath);
        System.out.println("生成文件：" + savePath);

        if (!module.getPersistance().equals("mybatis") && !module.getPersistance().equals("jpa")) {
            File implDir = getCoreSaveFilePath(module, module.getDaoPackage() + File.separator + module.getDaoImplPackage());
            templateName = "HibernateDaoImpl";
            generateMapperFile(table, module);
            if (module.getPersistance().equals("jdbc")) {
                templateName = "JdbcDaoImpl_" + config.getDb().getDbType();
            }
            File implFile = new File(implDir, table.getEntityName() + "DaoImpl.java");
            String implPath = implFile.getAbsolutePath();
            FreemarkerUtil.createDoc(obj, templateName, implPath);
            System.out.println("生成文件：" + implPath);
        }
        /**
         * 如果是mybatis，则生成mytabis的xml配置文件
         */
        else if (module.getPersistance().equals("mybatis")) {
            //配置了模块文件保存，则把文件全部生成到此目录下
            if (!CodeUtil.isEmpty(module.getSavePath())) {
                saveDir = new File(module.getSavePath());
            } else {
                saveDir = getMapperFilePath(module, module.getMapperPackage());
            }
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            File implFile = new File(saveDir, table.getEntityName() + "Mapper.xml");
            String implPath = implFile.getAbsolutePath();
            FreemarkerUtil.createDoc(obj, "MyBatis_" + config.getDb().getDbType(), implPath);
            System.out.println("生成文件：" + implPath);
        }
    }

    /**
     * 生成service文件
     *
     * @param table
     */
    private void generateServiceFile(Table table, Module module) {
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        /*File saveDir = getCoreSaveFilePath(module, module.getServicePackage());
        File saveFile = new File(saveDir, table.getEntityName() + "Service.java");
        String savePath = saveFile.getAbsolutePath();
        System.out.println("生成文件：" + savePath);
        FreemarkerUtil.createDoc(obj, "ServiceInterface1", savePath);*/

        //实现文件
        File implDir = getCoreSaveFilePath(module, module.getServicePackage());
        File implFile = new File(implDir, table.getEntityName() + "Service.java");
        String implPath = implFile.getAbsolutePath();
        String templateName = "ServiceImpl1";
        if (module.getPersistance().equals("jpa")) {
            templateName = "ServiceImpl_jpa";
        }
        FreemarkerUtil.createDoc(obj, templateName, implPath);
        System.out.println("生成文件：" + implPath);
    }

    /**
     * 生成Action
     *
     * @param table
     * @param module
     */
    private void generateActionFile(Table table, Module module) {

        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        String moduleName = CodeUtil.firstLetterToUpperCase(module.getName());

        //生成param
        File saveParamDir = getCoreSaveFilePath(module, module.getActionPackage() + File.separator + "param");
        File saveParamFile = new File(saveParamDir, table.getEntityName() + "Param.java");
        String savePath = saveParamFile.getAbsolutePath();
        FreemarkerUtil.createDoc(obj, "param", savePath);

        File saveDir = getCoreSaveFilePath(module, module.getActionPackage());
        File saveFile = new File(saveDir, table.getEntityName() + "Controller.java");

        String templateName = "Action";
        String templateMappingName = "mapping";
        if (module.getFramework().equals("mvc")) {
            templateName = "Action_" + module.getFramework() + "1";
            templateMappingName = "Action_" + module.getFrameworkMapping() + '1';
        }
        savePath = saveFile.getAbsolutePath();
        FreemarkerUtil.createDoc(obj, templateName, savePath);

        File saveMappingFile = new File(saveDir, table.getEntityName() + "ControllerMappingURL.java");
        savePath = saveMappingFile.getAbsolutePath();
        FreemarkerUtil.createDoc(obj, templateMappingName, savePath);
        System.out.println("生成文件：" + savePath);
    }

    /**
     * 生成指定表对象对应的视图文件
     *
     * @param table
     */
    private void generateViewFile(Table table, Module module) {
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        File saveDir = getCoreSaveFilePath(module, module.getViewPackage());
        File saveFile = new File(saveDir, table.getEntityCamelName() + ".view.xml");

        String savePath = saveFile.getAbsolutePath();
        System.out.println("生成文件：" + savePath);
        FreemarkerUtil.createDoc(obj, "View", savePath);
    }

    /**
     * 根据模块定义生成文件业务文件保存目录
     *
     * @param module
     * @param packageName
     * @return
     */
    private File getJspFilePath(Module module, String packageName) {
        //模块的名称,如果存在.则替换成/
        String moduleName = module.getName().replace(".", "/");
        File saveDir;
        //文件路径E:/eclipse/workspace/TestProject/src/com.zhaiugo/模块名/包名
        if (CodeUtil.isEmpty(module.getSavePath())) {
            saveDir = new File(
                    config.getBaseDir() + File.separator +
                            config.getBasePackage().replace(".", File.separator) + File.separator +
                            "pages" +
                            File.separator + moduleName + File.separator);
        } else {
            saveDir = new File(module.getSavePath(), moduleName + File.separator + packageName);
        }
        System.out.println("========saveDir============" + saveDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveDir;
    }

    /**
     * 根据模块定义生成文件业务文件保存目录
     *
     * @param module
     * @param packageName
     * @return
     */
    private File getMapperFilePath(Module module, String packageName) {
        //模块的名称,如果存在.则替换成/
        String moduleName = module.getName().replace(".", "/");
        File saveDir;
        //文件路径E:/eclipse/workspace/TestProject/src/com.zhaiugo/模块名/包名
        if (CodeUtil.isEmpty(module.getSavePath())) {
            saveDir = new File(
                    config.getBaseDir() + File.separator +
                            config.getBasePackage().replace(".", File.separator) + File.separator +
                            packageName +
                            File.separator + moduleName + File.separator);
        } else {
            saveDir = new File(module.getSavePath(), moduleName + File.separator + packageName);
        }
        System.out.println("========saveDir============" + saveDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveDir;
    }

    /**
     * 生成指定表对象对应的JSP视图文件
     *
     * @param table
     */
    private void generateJSPViewFile(Table table, Module module) {
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        File saveDir = getJspFilePath(module, "jsp");

        //生成列表页面
        File saveFile = null;
        if (null != module.getListPage() && !"".equals(module.getListPage())) {
            saveFile = new File(saveDir, module.getName() + "_" + module.getListPage() + ".html");
        } else {
            saveFile = new File(saveDir, module.getName() + ".html");
        }
        String savePath = saveFile.getAbsolutePath();
        System.out.println("生成文件：" + savePath);
        FreemarkerUtil.createDoc(obj, "oper_list1", savePath);

        //生成新增页面
        saveFile = new File(saveDir, module.getName() + "_" + module.getAddPage() + ".html");
        savePath = saveFile.getAbsolutePath();
        System.out.println("生成文件：" + savePath);
        FreemarkerUtil.createDoc(obj, "oper_add1", savePath);

        //生成修改页面
        saveFile = new File(saveDir, module.getName() + "_" + module.getModifyPage() + ".html");
        savePath = saveFile.getAbsolutePath();
        System.out.println("生成文件：" + savePath);
        FreemarkerUtil.createDoc(obj, "oper_modify1", savePath);

        saveFile = new File(saveDir, module.getName() + "_" + module.getDetailPage() + ".html");
        savePath = saveFile.getAbsolutePath();
        System.out.println("生成文件：" + savePath);
        FreemarkerUtil.createDoc(obj, "oper_detail", savePath);

        //此处不需添加js文件,如果是冠梦之前的框架请把该注释去掉,用于生成每个模块对应的js处理逻辑文件
        //生成对应的js文件
        /*saveDir = new File(saveDir,"js" + File.separator);
    	if(!saveDir.exists()){
    		saveDir.mkdir();
    	}
    	saveFile = new File(saveDir,module.getName() + ".js");
    	savePath =saveFile.getAbsolutePath();
    	System.out.println("生成文件："+savePath);
    	//FreemarkerUtil.createDoc(obj, "oper_js", savePath);
*/
    }

    /**
     * 生成dorado的公用model文件
     *
     * @param table
     * @param module
     * @return
     */
    private String generateDoradoModelString(Table table, Module module) {
        JSONObject obj = (JSONObject) JSON.toJSON(table);
        setBaseInfo(obj, module);
        String str = FreemarkerUtil.createString(obj, "DoradoModel");
        //System.out.println(str);
        return str;
    }

    /**
     * 写入model文件，如果model已存在，则增加内容
     *
     * @param fileName
     * @param content
     * @throws IOException
     */
    private void writeModelFile(String fileName, String content) throws IOException {
        FileOutputStream fos = null;
        Writer out = null;
        try {
            File dir = new File(config.getBaseDir() + File.separator + "models");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File modelFile = new File(dir, fileName);
            if (modelFile.exists()) {
                //如果文件存在，则读取出文件，再增加参数中的内容后，再写回文件
                String cont = readFile(modelFile);
                content = cont.replace("</Model>", content + "\n</Model>");
            } else {
                content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Model>" + content + "</Model>";
            }
            //System.out.println(content);
            fos = new FileOutputStream(modelFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            //这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。  
            //out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
            out = new BufferedWriter(oWriter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        out.write(content);
        out.close();
        fos.close();
        System.out.println("生成文件：" + config.getBaseDir() + File.separator + "models" + File.separator + fileName);
    }

    /**
     * 读取文件内容到一个字符串中
     *
     * @param file
     * @return
     */
    private static String readFile(File file) {
        StringBuffer result = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result.append(s);
                result.append("\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        DataBase2File reverser = new DataBase2File(new GenerateCodeParam());
        reverser.generateFiles();
    }

}  


