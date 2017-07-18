package com.free.framework.core.user.controller.param;

import com.free.framework.plateform.common.controller.param.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2017/6/5.
 */
@Data
public class UserParam extends BaseParam {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 性別
     */
    private String sex;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 登录账号
     */
    private String loginCode;

    /**
     * 用户状态
     */
    private String status;
}
