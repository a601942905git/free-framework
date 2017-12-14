package com.free.framework.plateform.config.security;

import com.free.framework.plateform.util.web.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * com.free.framework.plateform.config.security.LoginSuccessHandler
 * 用户登录成功之后回调
 * @author lipeng
 * @dateTime 2017/12/14 11:08
 */
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //获得授权后可得到用户信息   可使用SUserService进行数据库操作
        User user = (User)authentication.getPrincipal();
        log.info("==============================");
        log.info("当前登录用户:{}", user.getUsername());
        log.info("当前登录用户ip地址:{}", AddressUtils.getIpAddress());
        log.info("==============================");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
