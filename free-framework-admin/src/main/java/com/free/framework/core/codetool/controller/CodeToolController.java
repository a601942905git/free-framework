package com.free.framework.core.codetool.controller;

import com.alibaba.druid.filter.config.ConfigTools;
import com.free.framework.code.tools.DataBase2File;
import com.free.framework.code.tools.param.GenerateCodeParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

/**
 * com.free.framework.core.codetool.controller.CodeToolController
 * 代码生成
 * @author lipeng
 * @dateTime 2017/9/10 10:54
 */
@Controller
@RequestMapping(CodeToolControllerMappingURL.CODE_TOOL_CONTROLLER)
@Slf4j
public class CodeToolController {

    @Value("${db.type}")
    private String dbType;

    @Value("${db.name}")
    private String dbName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driverClassName}")
    private String dbDriver;

    @Value("${publicKey}")
    private String publicKey;

    @GetMapping(CodeToolControllerMappingURL.CODE_TOOL)
    @ResponseBody
    public int generateCodeTool() throws Exception {
        int count = 0;
        GenerateCodeParam generateCodeParam = buildGenerateCodeParam();
        DataBase2File dataBase2File = new DataBase2File(generateCodeParam);
        try {
            dataBase2File.generateFiles();
            count = 1;
        } catch (IOException e) {
            log.error("【CodeToolController中generateCodeTool,IO异常】{}", e);
        } catch (ClassNotFoundException e) {
            log.error("【CodeToolController中generateCodeTool,ClassNotFoundException异常】{}", e);
        } catch (SQLException e) {
            log.error("【CodeToolController中generateCodeTool,SQLException异常】{}", e);
        }
        return count;
    }

    /**
     * 构建生成参数
     * @return
     */
    private GenerateCodeParam buildGenerateCodeParam() throws Exception {
        GenerateCodeParam generateCodeParam =
                GenerateCodeParam.builder()
                        .baseDir("D://free-framework")
                        .basePackage("com.free.framework")
                        .baseCorePackage("core")
                        .baseWebPackage("")
                        .dbType(dbType)
                        .dbUser(dbUserName)
                        .dbPwd(ConfigTools.decrypt(publicKey, dbPassword))
                        .dbName(dbName)
                        .dbUrl(dbUrl)
                        .dbDriver(dbDriver)
                        .daoPackage("mapper")
                        .servicePackage("service")
                        .serviceImplPackage("")
                        .entityPackage("entity")
                        .mapperPackage("resources/mapper")
                        .actionPackage("controller")
                        .listPage("list")
                        .addPage("add")
                        .modifyPage("update")
                        .detailPage("detail")
                        .isDeleteTablePrefix(true)
                        .prefix("free_")
                        .persistanceFramework("mybatis")
                        .framework("mvc")
                        .frameworkMapping("mapping")
                        .excludeColumns("SAVE_DATE&SAVE_PERSON&UPDATE_DATE&UPDATE_PERSON&STATUS&")
                        .build();
        return generateCodeParam;
    }
}
