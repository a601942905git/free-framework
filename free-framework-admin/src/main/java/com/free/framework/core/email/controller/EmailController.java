package com.free.framework.core.email.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.free.framework.core.email.EmailController
 *
 * @author lipeng
 * @dateTime 2017/11/6 22:53
 */
@Controller
@RequestMapping(EmailControllerMappingUrl.EMAIL_CONTROLLER)
public class EmailController {

    @GetMapping(EmailControllerMappingUrl.EMAIL)
    public void sendEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("601942905@qq.com");
        simpleMailMessage.setTo("a601942905@163.com");
        simpleMailMessage.setSubject("主题:简单邮件");
        simpleMailMessage.setText("测试邮件发送功能");
    }
}
