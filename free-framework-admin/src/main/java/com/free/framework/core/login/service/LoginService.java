package com.free.framework.core.login.service;

import com.free.framework.core.login.param.LoginParam;
import com.free.framework.core.user.constants.UserConstants;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.service.UserService;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.util.web.WebContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.free.framework.core.login.service.LoginService
 * 登录请求业务
 * @author lipeng
 * @dateTime 2017/9/17 3:33
 */
@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @param loginParam    登陆信息
     * @return
     */
    public ResponseData login(LoginParam loginParam) {
        String loginCode = loginParam.getLoginCode();
        String loginPassword = loginParam.getLoginPassword();

        User user = userService.getUserByLoginCode(loginCode).orElse(null);
        //  账号不存在
        if (null == user) {
            return ResponseData.builder()
                    .code(ResponseData.RESULT_CODE.USER_NOT_EXISTS_CODE)
                    .message(ResponseData.RESULT_MESSAGE.USER_NOT_EXISTS_MESSAGE)
                    .build();
        }

        String userPassword = user.getLoginPassword();
        loginPassword = UserUtils.generateEncryptPassword(loginCode, loginPassword);
        // 密码错误
        if (!userPassword.equalsIgnoreCase(loginPassword)) {
            return ResponseData.builder()
                    .code(ResponseData.RESULT_CODE.USER_PASSWORD_ERROR_CODE)
                    .message(ResponseData.RESULT_MESSAGE.USER_PASSWORD_ERROR_MESSAGE)
                    .build();
        }

        WebContextUtils.setSessionAttribute(UserConstants.USER_LOGIN_CODE_SESSION_KEY, user.getLoginCode());
        WebContextUtils.setSessionAttribute(UserConstants.USER_SESSION_KEY, user);
        return ResponseData.success();
    }
}
