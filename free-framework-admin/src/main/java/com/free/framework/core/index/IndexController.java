package com.free.framework.core.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2017/6/3.
 * @author lipeng
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
