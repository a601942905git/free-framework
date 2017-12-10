package com.free.framework.core.user.mapper;

import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * com.free.framework.core.user.mapper.UserMapper
 * 用户数据库操作
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Mapper
public interface UserMapper {

    /**
     * 查询用户列表信息
     * @return
     */
    List<User> listUser(UserParam userParam);

    /**
     * 查询用户详情信息
     * @param id
     * @return
     */
    User getUser(Integer id);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    Integer saveUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 根据登陆账号查询用户信息
     * @param loginCode 登陆账号
     * @return
     */
    User getUserByLoginCode(String loginCode);
}
