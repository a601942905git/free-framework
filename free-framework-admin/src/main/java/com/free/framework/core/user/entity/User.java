package com.free.framework.core.user.entity;

import com.free.framework.core.role.entity.Role;
import com.free.framework.plateform.common.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author smile
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
     * 用户角色
     */
    private List<Role> roleList;
}
