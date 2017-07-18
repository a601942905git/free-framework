package com.free.framework.core.user.controller;


import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.service.UserService;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.constant.StatusEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
@RequestMapping(UserControllerMappingURL.USER_CONTROLLER)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表信息
     * @param model 存放后台数据用于前台展示
     * @return
     */
    @GetMapping(UserControllerMappingURL.USER)
    public String list(Model model, UserParam userParam) {
        PageInfo pageInfo = userService.pageUser(userParam);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("userParam", userParam);
        return UserControllerMappingURL.PAGE_LIST_RETURN;
    }

    /**
     * 查询用户详情信息
     * @param id     用户编号
     * @param model 存放后台数据用于前台展示
     * @return
     */
    @GetMapping(UserControllerMappingURL.ONE_USER)
    public String getDetail(@PathVariable(ID) Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return UserControllerMappingURL.PAGE_DETAIL_RETURN;
    }

    /**
     * 新增用户界面
     * @return
     */
    @GetMapping(UserControllerMappingURL.PAGE_ADD)
    public String addPage() {
        return UserControllerMappingURL.PAGE_ADD_RETURN;
    }

    /**
     * 新增用戶信息
     s* @param user  用户对象
     * @return
     */
    @PostMapping(UserControllerMappingURL.USER)
    @ResponseBody
    public Integer save(User user) {
        user.setSavePerson("111111");
        user.setSaveDate(new Date());
        user.setStatus(StatusEnum.ENABLE_STATUS.getId());
        Integer count = userService.saveUser(user);
        return count;
    }

    /**
     * 修改用户界面
     * @return
     */
    @GetMapping(UserControllerMappingURL.PAGE_UPDATE)
    public String updatePage(Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return UserControllerMappingURL.PAGE_UPDATE_RETURN;
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    @PutMapping(UserControllerMappingURL.USER)
    @ResponseBody
    public Integer update(User user) {
        user.setUpdatePerson("111111");
        user.setUpdateDate(new Date());
        Integer count = userService.updateUser(user);
        return count;
    }
}
