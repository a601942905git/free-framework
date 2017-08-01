package com.free.framework.core.user.service;

import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.mapper.UserMapper;
import com.free.framework.plateform.common.service.CommonService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Service
@Slf4j
public class UserService extends CommonService {

    /**
     * 缓存用户列表的key
     */
    private static final String LIST_USER_KEY = "userList";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询用户列表信息
     * @param userParam  查询条件
     * @return
     */
    public PageInfo<User> pageUser(UserParam userParam) {
       /* ListOperations<String, PageInfo> listOperations = redisTemplate.opsForList();
        boolean hasKey = redisTemplate.hasKey(LIST_USER_KEY);
        if (hasKey) {
            PageInfo<User> pageUser = listOperations.index(LIST_USER_KEY, 0);
            LOGGER.info("===============从缓存中获取用户列表数据信息{}", pageUser);
            return pageUser;
        }*/
        // 分页
        startPage(userParam);
        // 用户列表
        List<User> userList = userMapper.listUser(userParam);
        // 设置分页信息
        PageInfo<User> pageUser = new PageInfo(userList);
        /*listOperations.leftPush(LIST_USER_KEY, pageUser);*/
        return pageUser;
    }

    /**
     * 查询用户详情信息
     * @param id    用户编号
     * @return
     */
    public User getUser(Integer id) {
        User user = userMapper.getUser(id);
        return user;
    }

    /**
     * 新增用户信息
     * @param user  用户对象
     * @return
     */
    public Integer saveUser(User user) {
        Integer count = userMapper.saveUser(user);
        return count;
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    public Integer updateUser(User user) {
        Integer count = userMapper.updateUser(user);
        return count;
    }
}
