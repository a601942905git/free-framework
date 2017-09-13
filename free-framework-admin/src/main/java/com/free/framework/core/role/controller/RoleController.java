package com.free.framework.core.role.controller;

import com.free.framework.core.role.controller.param.RoleParam;
import com.free.framework.core.role.entity.Role;
import com.free.framework.core.role.service.RoleService;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.constant.StatusEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * com.free.framework.core.role.controller.RoleController
 * 角色控制器
 * @author lipeng
 * @dateTime 2017/9/9 23:04
 */
@Controller
@RequestMapping(RoleControllerMappingURL.ROLE_CONTROLLER)
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表信息
     * @param model 存放后台数据用于前台展示
     * @return
     */
    @GetMapping(RoleControllerMappingURL.ROLE)
    public String list(Model model, RoleParam roleParam) {
        PageInfo pageInfo = roleService.pageRole(roleParam);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("roleParam", roleParam);
        return RoleControllerMappingURL.PAGE_LIST_RETURN;
    }

    /**
     * 查询角色详情信息
     * @param id     角色编号
     * @param model 存放后台数据用于前台展示
     * @return String
     */
    @GetMapping(RoleControllerMappingURL.ONE_ROLE)
    public String getDetail(@PathVariable(ID) Integer id, Model model) {
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);
        return RoleControllerMappingURL.PAGE_DETAIL_RETURN;
    }

    /**
     * 新增角色界面
     * @return
     */
    @GetMapping(RoleControllerMappingURL.PAGE_ADD)
    public String addPage() {
        return RoleControllerMappingURL.PAGE_ADD_RETURN;
    }

    /**
     * 新增角色信息
     s* @param user  用户对象
     * @return
     */
    @PostMapping(RoleControllerMappingURL.ROLE)
    @ResponseBody
    public Integer save(Role role) {
        role.setSavePerson("111111");
        role.setSaveDate(new Date());
        role.setStatus(StatusEnum.ENABLE_STATUS.getId());
        Integer count = roleService.saveRole(role);
        return count;
    }

    /**
     * 修改用户界面
     * @return
     */
    @GetMapping(RoleControllerMappingURL.PAGE_UPDATE)
    public String updatePage(Integer id, Model model) {
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);
        return RoleControllerMappingURL.PAGE_UPDATE_RETURN;
    }

    /**
     * 修改用户信息
     * @param role  用户对象
     * @return
     */
    @PutMapping(RoleControllerMappingURL.ROLE)
    @ResponseBody
    public Integer update(Role role) {
        role.setUpdatePerson("111111");
        role.setUpdateDate(new Date());
        Integer count = roleService.updateRole(role);
        return count;
    }
}