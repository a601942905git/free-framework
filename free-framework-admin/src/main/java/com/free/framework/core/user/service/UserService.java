package com.free.framework.core.user.service;

import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.mapper.UserMapper;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.util.date.DateUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.free.framework.core.user.service.UserService
 * 用户业务操作
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Service
@Slf4j
public class UserService extends CommonService<User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户列表信息
     * @param userParam  查询条件
     * @return
     */
    @Cacheable("user-key")
    public PageInfo<User> pageUser(UserParam userParam) {
        // 分页
        startPage(userParam);
        // 用户列表
        List<User> userList = userMapper.listUser(userParam);
        return getPageInfo(userList);
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
    public ResponseData saveUser(User user) {
        String loginCode = user.getLoginCode();
        String loginPassword = user.getLoginPassword();
        String encryptPassword = UserUtils.generateEncryptPassword(loginCode, loginPassword);
        user.setLoginPassword(encryptPassword);
        user.setSavePerson(UserUtils.getUserLoginCode());
        user.setSaveDate(DateUtils.getCurrentDate());
        user.setStatus(StatusEnum.ENABLE_STATUS.getId());
        int count = userMapper.saveUser(user);
        return count == 1 ? ResponseData.success() : ResponseData.fail();
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    public ResponseData updateUser(User user) {
        user.setUpdatePerson(UserUtils.getUserLoginCode());
        user.setUpdateDate(DateUtils.getCurrentDate());
        int count = userMapper.updateUser(user);
        return count == 1 ? ResponseData.success() : ResponseData.fail();
    }
}
