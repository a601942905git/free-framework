package com.free.framework.plateform.config.filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.annotation.WebFilter;

/**
 * com.free.framework.plateform.config.filter.HttpMethodFilter
 * 请求动作过滤器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@WebFilter(
    filterName = "hiddenHttpMethodFilter",
    urlPatterns = "/*")
public class HttpMethodFilter extends HiddenHttpMethodFilter {
}
