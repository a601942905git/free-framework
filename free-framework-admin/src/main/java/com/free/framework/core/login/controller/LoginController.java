package com.free.framework.core.login.controller;

import com.free.framework.core.login.param.LoginParam;
import com.free.framework.core.login.service.LoginService;
import com.free.framework.mq.sender.ActiveMQSender;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.constant.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * com.free.framework.core.login.LoginController
 * 登录控制器
 * @author lipeng
 * @dateTime 2017/9/9 10:54
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ActiveMQSender activeMQSender;

    @Value("${spring.activemq.admin.destination}")
    private String destination;

    /**
     * 登陆
     * @param loginParam    登陆信息
     * @return  是否登陆成功
     */
    @PostMapping(LoginControllerMappingUrl.LOGIN)
    @ResponseBody
    public ResponseData login(LoginParam loginParam) {
        ResponseData responseData = loginService.login(loginParam);
        return responseData;
    }

    /**
     * 退出登陆
     * @param request   request对象
     * @return          登陆页面
     */
    @GetMapping(LoginControllerMappingUrl.LOG_OUT)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return SystemConstants.REDIRECT + LoginControllerMappingUrl.LOGIN_CONTROLLER;
    }
}