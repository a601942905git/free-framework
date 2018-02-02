package com.free.framework.plateform.interceptor;

import com.free.framework.util.http.HttpUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.LongAdder;

/**
 * com.free.framework.plateform.interceptor.RequestInterceptor
 *
 * @author lipeng
 * @dateTime 2018/1/30 14:56
 */
public class RequestInterceptor implements HandlerInterceptor{

    private static LongAdder REQUEST_COUNT = new LongAdder();

    private static final Long MAX_REQUEST_COUNT = 5L;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (REQUEST_COUNT.longValue() < 10) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
        }
        REQUEST_COUNT.increment();
        if (REQUEST_COUNT.longValue() > MAX_REQUEST_COUNT) {
            httpServletResponse.setHeader("context-type", "text/html;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write("前面还有" + REQUEST_COUNT.longValue() + "个人,请稍后重试!");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        REQUEST_COUNT.decrement();
    }

    public static void main(String[] args) {
        RequestInterceptor.REQUEST_COUNT = new LongAdder();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                HttpUtils.get("http://127.0.0.1:315/");
            }).start();
        }
    }
}
