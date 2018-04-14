package com.free.framework.core.user.entity;

import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;

/**
 * com.free.framework.core.user.entity.User
 * 用户实体类
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String face;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 登录账号
     */
    private String loginCode;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 用户所属组织
     */
    private Integer organizationId;

    /**
     * 用户所属组织
     */
    private String organizationName;
}
