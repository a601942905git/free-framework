package com.free.framework.core.role.controller;

import com.free.framework.core.role.controller.param.RoleParam;
import com.free.framework.core.role.entity.Role;
import com.free.framework.core.role.service.RoleService;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * com.free.framework.core.role.controller.RoleController
 * 角色请求控制器
 * @author lipeng
 * @dateTime 2017/9/9 23:04
 */
@Controller
@RequestMapping(RoleControllerMappingUrl.ROLE_CONTROLLER)
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表信息
     * @return
     */
    @GetMapping(RoleControllerMappingUrl.ROLE)
    public String list(RoleParam roleParam) {
        PageInfo pageInfo = roleService.pageRole(roleParam);
        setRequestAttribute("pageInfo", pageInfo);
        setRequestAttribute("roleParam", roleParam);
        return RoleControllerMappingUrl.PAGE_LIST_RETURN;
    }

    /**
     * 查询角色详情信息
     * @param id     角色编号
     * @return String
     */
    @GetMapping(RoleControllerMappingUrl.ONE_ROLE)
    public String getDetail(@PathVariable(ID) Integer id) {
        Role role = roleService.getRole(id);
        setRequestAttribute("role", role);
        return RoleControllerMappingUrl.PAGE_DETAIL_RETURN;
    }

    /**
     * 新增角色界面
     * @return
     */
    @GenerateToken
    @GetMapping(RoleControllerMappingUrl.PAGE_ADD)
    public String addPage() {
        return RoleControllerMappingUrl.PAGE_ADD_RETURN;
    }

    /**
     * 新增角色信息
     s* @param user  用户对象
     * @return
     */
    @ValidateToken
    @PostMapping(RoleControllerMappingUrl.ROLE)
    @ResponseBody
    public ResponseData save(Role role) {
        ResponseData responseData = roleService.saveRole(role);
        return responseData;
    }

    /**
     * 修改用户界面
     * @return
     */
    @GenerateToken
    @GetMapping(RoleControllerMappingUrl.PAGE_UPDATE)
    public String updatePage(Integer id) {
        Role role = roleService.getRole(id);
        setRequestAttribute("role", role);
        return RoleControllerMappingUrl.PAGE_UPDATE_RETURN;
    }

    /**
     * 修改用户信息
     * @param role  用户对象
     * @return
     */
    @ValidateToken
    @PutMapping(RoleControllerMappingUrl.ONE_ROLE)
    @ResponseBody
    public ResponseData update(Role role) {
        ResponseData responseData = roleService.updateRole(role);
        return responseData;
    }
}
