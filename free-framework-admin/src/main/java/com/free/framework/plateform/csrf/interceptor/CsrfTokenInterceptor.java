package com.free.framework.plateform.csrf.interceptor;


import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.RefreshToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.free.framework.plateform.util.web.WebContextUtils;
import com.free.framework.util.csrf.CsrfTokenUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * csrfToken拦截器
 * @author lipeng
 */
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        ValidateToken validateToken = method.getAnnotation(ValidateToken.class);
        if (null != validateToken && validateToken.vlidate()) {
            String requestToken = request.getParameter(CSRF_TOKEN);
            boolean validateTokenFlag = validateToken(requestToken);
            System.out.println("CsrfToken验证结果======>" + validateTokenFlag);
            // 验证失败
            if (!validateTokenFlag) {
                return false;
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
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 生成token
        GenerateToken generateToken = method.getAnnotation(GenerateToken.class);
        if (null != generateToken && generateToken.generate()) {
            WebContextUtils.setSessionAttribute(CSRF_TOKEN, CsrfTokenUtils.generateToken());
        }

        // 刷新token
        RefreshToken refreshToken = method.getAnnotation(RefreshToken.class);
        if (null != refreshToken && refreshToken.refresh()) {
            WebContextUtils.setSessionAttribute(CSRF_TOKEN, CsrfTokenUtils.generateToken());
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
