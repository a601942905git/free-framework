package com.free.framework.core.email.controller;

import com.free.framework.core.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * com.free.framework.core.email.EmailController
 *
 * @author lipeng
 * @dateTime 2017/11/6 22:53
 */
@Slf4j
@Controller
@RequestMapping(EmailControllerMappingUrl.EMAIL_CONTROLLER)
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping(EmailControllerMappingUrl.EMAIL)
    @ResponseBody
    public void sendEmail() {
        emailService.sendSimpleMail("601942905@qq.com", "测试主题", "测试邮件功能...");
    }
}
