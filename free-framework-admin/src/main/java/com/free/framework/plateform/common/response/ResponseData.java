package com.free.framework.plateform.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.free.framework.plateform.common.response.ResponseData
 *
 * @author lipeng
 * @dateTime 2017/9/17 3:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData <T> {

    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功
     * @return  返回成功数据
     */
    public static ResponseData success(){
        return ResponseData.builder()
                .code(RESULT_CODE.SUCCESS_CODE)
                .message(RESULT_MESSAGE.SUCCESS_MESSAGE)
                .build();
    }

    /**
     * 成功
     * @return  返回失败数据
     */
    public static ResponseData fail() {
        return ResponseData.builder()
                .code(RESULT_CODE.FAILED_CODE)
                .message(RESULT_MESSAGE.FAILED_MESSAGE)
                .build();
    }

    public enum RESULT_CODE {
        ;
        public static final String SUCCESS_CODE = "1";
        public static final String FAILED_CODE = "-1";

        /**
         * 用户不存在
         */
        public static final String USER_NOT_EXISTS_CODE = "100001";

        /**
         * 用户密码错误
         */
        public static final String USER_PASSWORD_ERROR_CODE = "100002";
    }

    public enum RESULT_MESSAGE {
        ;
        public static final String SUCCESS_MESSAGE = "成功";
        public static final String FAILED_MESSAGE = "失败";

        /**
         * 未知用户
         */
        public static final String USER_NOT_EXISTS_MESSAGE = "用户不存在";

        /**
         * 密码错误
         */
        public static final String USER_PASSWORD_ERROR_MESSAGE = "100002";
    }
}
