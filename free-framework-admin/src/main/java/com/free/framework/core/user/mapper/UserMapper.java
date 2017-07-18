package com.free.framework.core.user.mapper;

import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
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
}
