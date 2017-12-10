package com.free.framework.core.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * com.free.framework.core.index.controller.IndexController
 * 首页控制器
 * @author lipeng
 * @dateTime 2017/11/6 22:53
 */
@Controller
public class IndexController {

    /**
     * 程序首页访问路径
     */
    public static final String INDEX_CONTROLLER = "/index";

    /**
     * 程序首页访问路径返回页面
     */
    public static final String ROOT_PATH_RETURN_PAGE = "index/index";

    @GetMapping(INDEX_CONTROLLER)
    public String index() {
        return ROOT_PATH_RETURN_PAGE;
    }
}
