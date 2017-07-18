package com.free.framework.core.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
public class IndexController {

    /**
     * 程序首页访问路径
     */
    public static final String ROOT_PATH = "/";

    /**
     * 程序首页访问路径返回页面
     */
    public static final String ROOT_PATH_RETURN_PAGE = "index/index";

    @RequestMapping(ROOT_PATH)
    public String index() {
        return ROOT_PATH_RETURN_PAGE;
    }
}
