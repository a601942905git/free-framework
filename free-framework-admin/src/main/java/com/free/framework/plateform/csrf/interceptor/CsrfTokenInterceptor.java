package com.free.framework.plateform.csrf.interceptor;


import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.free.framework.plateform.util.web.WebContextUtils;
import com.free.framework.util.csrf.CsrfTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * com.free.framework.plateform.csrf.interceptor.CsrfTokenInterceptor
 * csrf token拦截器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Slf4j
public class CsrfTokenInterceptor implements HandlerInterceptor{

    public static final String CSRF_TOKEN = "csrfToken";

    /**
     * request请求处理之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 此处一定要对类型判断,次handler并非一定是HandlerMethod
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            ValidateToken validateToken = method.getAnnotation(ValidateToken.class);
            if (null != validateToken && validateToken.vlidate()) {
                String requestToken = request.getParameter(CSRF_TOKEN);
                boolean validateTokenFlag = validateToken(requestToken);
                log.info("CsrfToken验证结果======>" + validateTokenFlag);
                // 验证失败
                if (!validateTokenFlag) {
                    return false;
                }

                // 验证通过移除csrfToken
                WebContextUtils.removeSessionAttribute(CSRF_TOKEN);
            }
        }

        return true;
    }

    /**
     * 验证请求的token
     * @param requestToken
     * @return
     */
    private boolean validateToken(String requestToken) {
        String sessionCsrfToken = (String) WebContextUtils.getSessionAttribute(CSRF_TOKEN);
        return Objects.equals(requestToken, sessionCsrfToken);
    }

    /**
     * request请求处理中,渲染视图之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            // 生成token
            GenerateToken generateToken = method.getAnnotation(GenerateToken.class);
            if (null != generateToken && generateToken.generate()) {
                WebContextUtils.setSessionAttribute(CSRF_TOKEN, CsrfTokenUtils.generateToken());
            }
        }
    }

    /**
     * 请求结束
     * @param request
     * @param response
     * @param handler
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
