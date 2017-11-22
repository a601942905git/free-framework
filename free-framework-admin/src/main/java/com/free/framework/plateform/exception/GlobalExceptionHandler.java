package com.free.framework.plateform.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常处理视图
     */
    public static final String DEFAULT_ERROR_VIEW = "error/error";

    /**
     * 当控制器方法抛出exception异常,执行该方法
     * 根据异常类型执行对应的方法
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
