package com.free.framework.core.user.service;

import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.mapper.UserMapper;
import com.free.framework.plateform.common.service.CommonService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Service
@Slf4j
public class UserService extends CommonService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户列表信息
     * @param userParam  查询条件
     * @return
     */
    public PageInfo<User> pageUser(UserParam userParam) {
        // 分页
        startPage(userParam);
        // 用户列表
        List<User> userList = userMapper.listUser(userParam);
        // 设置分页信息
        PageInfo<User> pageUser = new PageInfo(userList);
        return pageUser;
    }

    /**
     * 查询用户详情信息
     * @param id    用户编号
     * @return
     */
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    /**
     * 通过登陆账号查询用户信息
     * @param loginCode 登陆账号
     * @return
     */
    public User getUserByLoginCode(String loginCode) {
        return userMapper.getUserByLoginCode(loginCode);
    }

    /**
     * 新增用户信息
     * @param user  用户对象
     * @return
     */
    public Integer saveUser(User user) {
        return userMapper.saveUser(user);
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
