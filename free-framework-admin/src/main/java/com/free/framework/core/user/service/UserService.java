package com.free.framework.core.user.service;

import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.mapper.UserMapper;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.NumberConstants;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.util.date.DateUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getUser(Integer id) {
        User user = userMapper.getUser(id);
        return getOptional(user);
    }

    /**
     * 通过登陆账号查询用户信息
     * @param loginCode 登陆账号
     * @return
     */
    public Optional<User> getUserByLoginCode(String loginCode) {
        User user = userMapper.getUserByLoginCode(loginCode);
        return getOptional(user);
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
        user.setSaveDate(DateUtils.getDate());
        user.setStatus(StatusEnum.ENABLE_STATUS.getId());
        int count = userMapper.saveUser(user);
        return count == NumberConstants.ONE ? ResponseData.success() : ResponseData.fail();
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    public ResponseData updateUser(User user) {
        user.setUpdatePerson(UserUtils.getUserLoginCode());
        user.setUpdateDate(DateUtils.getDate());
        int count = userMapper.updateUser(user);
        return count == NumberConstants.ONE ? ResponseData.success() : ResponseData.fail();
    }
}
