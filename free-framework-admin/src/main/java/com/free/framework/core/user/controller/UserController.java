package com.free.framework.core.user.controller;


import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.service.UserService;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/6/3.
 * @author lipeng
 */
@Controller
@RequestMapping(UserControllerMappingURL.USER_CONTROLLER)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表信息
     * @param userParam 查询请求参数
     * @return
     */
    @GetMapping(UserControllerMappingURL.USER)
    public String listUser(UserParam userParam) {
        PageInfo pageInfo = userService.pageUser(userParam);
        setRequestAttribute("pageInfo", pageInfo);
        setRequestAttribute("userParam", userParam);
        return UserControllerMappingURL.PAGE_LIST_RETURN;
    }

    /**
     * 查询用户详情信息
     * @param id     用户编号
     * @return
     */
    @GetMapping(UserControllerMappingURL.ONE_USER)
    public String getDetail(@PathVariable(ID) Integer id) {
        User user = userService.getUser(id);
        setRequestAttribute("user", user);
        return UserControllerMappingURL.PAGE_DETAIL_RETURN;
    }

    /**
     * 新增用户界面
     * @return
     */
    @GenerateToken
    @GetMapping(UserControllerMappingURL.PAGE_ADD)
    public String addPage() {
        return UserControllerMappingURL.PAGE_ADD_RETURN;
    }

    /**
     * 新增用戶信息
     s* @param user  用户对象
     * @return
     */
    @ValidateToken
    @PostMapping(UserControllerMappingURL.USER)
    @ResponseBody
    public ResponseData save(User user) {
        ResponseData responseData = userService.saveUser(user);
        return responseData;
    }

    /**
     * 修改用户界面
     * @return
     */
    @GenerateToken
    @GetMapping(UserControllerMappingURL.PAGE_UPDATE)
    public String updatePage(Integer id) {
        User user = userService.getUser(id);
        setRequestAttribute("user", user);
        return UserControllerMappingURL.PAGE_UPDATE_RETURN;
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    @ValidateToken
    @PutMapping(UserControllerMappingURL.USER)
    @ResponseBody
    public ResponseData update(User user) {
        ResponseData responseData = userService.updateUser(user);
        return responseData;
    }
}
