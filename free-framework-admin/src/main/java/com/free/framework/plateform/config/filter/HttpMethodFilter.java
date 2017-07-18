package com.free.framework.plateform.config.filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by Administrator on 2017/6/13.
 */
@WebFilter(
    filterName = "hiddenHttpMethodFilter",
    urlPatterns = "/*")
public class HttpMethodFilter extends HiddenHttpMethodFilter {
}
