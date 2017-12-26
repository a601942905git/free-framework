package com.free.framework.core.role.service;

import com.free.framework.core.role.controller.param.RoleParam;
import com.free.framework.core.role.entity.Role;
import com.free.framework.core.role.mapper.RoleMapper;
import com.free.framework.core.user.util.UserUtils;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.common.service.CommonService;
import com.free.framework.plateform.constant.StatusEnum;
import com.free.framework.util.date.DateUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.free.framework.core.role.service.RoleService
 * 角色业务操作
 * @author lipeng
 * @dateTime 2017/9/9 23:04
 */
@Service
@Slf4j
public class RoleService extends CommonService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色列表信息
     * @param roleParam  查询条件
     * @return
     */
    public PageInfo<Role> pageRole(RoleParam roleParam) {
        // 分页
        startPage(roleParam);
        // 角色列表
        List<Role> rolesList = roleMapper.listRole(roleParam);
        return getPageInfo(rolesList);
    }

    /**
     * 查询角色详情信息
     * @param id    角色编号
     * @return
     */
    public Role getRole(Integer id) {
        return roleMapper.getRole(id);
    }

    /**
     * 新增角色信息
     * @param role  角色对象
     * @return
     */
    public ResponseData saveRole(Role role) {
        role.setSavePerson(UserUtils.getUserLoginCode());
        role.setSaveDate(DateUtils.getDate());
        role.setStatus(StatusEnum.ENABLE_STATUS.getId());
        int count = roleMapper.saveRole(role);
        return count == 1 ? ResponseData.success() : ResponseData.fail();
    }

    /**
     * 修改角色信息
     * @param role  角色对象
     * @return
     */
    public ResponseData updateRole(Role role) {
        role.setUpdatePerson(UserUtils.getUserLoginCode());
        role.setUpdateDate(DateUtils.getDate());
        int count = roleMapper.updateRole(role);
        return count == 1 ? ResponseData.success() : ResponseData.fail();
    }
}
