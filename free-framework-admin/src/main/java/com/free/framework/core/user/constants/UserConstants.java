package com.free.framework.core.user.constants;

/**
 * com.free.framework.core.user.constants.UserConstants
 * 用户常量操作
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
public interface UserConstants {

    /**
     * 用于用户密码加密的key
     */
    public static final String USER_ENCRYPT_KEY = "5b20e115-56bb-4189-b25d-5c81bd986df2";

    /**
     * 存放用户编号的key
     */
    public static final String USER_LOGIN_CODE_SESSION_KEY = "loginCode";

    /**
     * 存放用户信息的key
     */
    public static final String USER_SESSION_KEY = "loginUser";
}
