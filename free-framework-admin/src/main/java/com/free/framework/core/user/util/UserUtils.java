package com.free.framework.core.user.util;

import com.free.framework.core.user.constants.UserConstants;
import com.free.framework.core.user.entity.User;
import com.free.framework.plateform.util.web.WebContextUtils;
import com.free.framework.util.encrypt.MD5Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * com.free.framework.core.user.util.UserUtils
 *
 * @author lipeng
 * @dateTime 2017/9/17 3:28
 */
@Slf4j
public class UserUtils {

    /**
     * 生成加密密码
     * @param loginCode         登陆账号
     * @param loginPassword     登陆密码
     * @return
     */
    public static String generateEncryptPassword(String loginCode, String loginPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append(loginCode);
        sb.append(loginPassword);
        sb.append(UserConstants.USER_ENCRYPT_KEY);
        String result = sb.toString();
        try {
            return MD5Utils.encode(result);
        } catch (Exception e) {
            log.error("【UserUtils中generateEncryptPassword】加密异常:{}", e);
            return result;
        }
    }

    /**
     * 获取当前登陆用户账号
     * @return  登陆账号
     */
    public static String getUserLoginCode() {
        return (String) WebContextUtils.getSessionAttribute(UserConstants.USER_LOGIN_CODE_SESSION_KEY);
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public static User getUser() {
        return (User) WebContextUtils.getSessionAttribute(UserConstants.USER_SESSION_KEY);
    }
}
