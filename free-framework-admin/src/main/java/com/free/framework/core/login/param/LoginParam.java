package com.free.framework.core.login.param;

import lombok.Data;

/**
 * com.free.framework.core.login.param.LoginParam
 * 登录请求参数
 * @author lipeng
 * @dateTime 2017/9/17 3:09
 */
@Data
public class LoginParam {

    /**
     * 登陆账号
     */
    private String loginCode;

    /**
     * 登陆密码
     */
    private String loginPassword;
}
