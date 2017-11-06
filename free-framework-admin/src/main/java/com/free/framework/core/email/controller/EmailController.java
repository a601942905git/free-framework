package com.free.framework.core.email.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    JavaMailSender javaMailSender;

    @GetMapping(EmailControllerMappingUrl.EMAIL)
    @ResponseBody
    public void sendEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("a601942905@163.com");
        simpleMailMessage.setTo("601942905@qq.com");
        simpleMailMessage.setSubject("主题:简单邮件");
        simpleMailMessage.setText("测试邮件发送功能");
        try {
            javaMailSender.send(simpleMailMessage);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.info("邮件发送失败:", e);
        }
    }
}
