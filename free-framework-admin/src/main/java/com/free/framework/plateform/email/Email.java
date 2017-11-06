package com.free.framework.plateform.email;

import lombok.*;

/**
 * com.free.framework.plateform.email.Email
 *
 * @author lipeng
 * @dateTime 2017/11/6 22:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {

    /**
     * 源邮箱
     */
    private String from;

    /**
     * 目标邮箱
     */
    private String to;

    /**
     * 发送主题
     */
    private String subject;

    /**
     * 发送文本
     */
    private String text;
}
