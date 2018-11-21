package com.free.framework.core.user.controller;


import com.free.framework.core.user.controller.param.UserParam;
import com.free.framework.core.user.entity.User;
import com.free.framework.core.user.service.UserService;
import com.free.framework.plateform.common.controller.BaseController;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.csrf.annotation.GenerateToken;
import com.free.framework.plateform.csrf.annotation.ValidateToken;
import com.free.framework.plateform.util.web.WebContextUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * com.free.framework.core.user.controller.UserController
 * 用户请求控制器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Controller
@RequestMapping(UserControllerMappingUrl.USER_CONTROLLER)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表信息
     * @param userParam 查询请求参数
     * @return
     */
    @GetMapping(UserControllerMappingUrl.USER)
    public String listUser(UserParam userParam) {
        HttpServletRequest request = WebContextUtils.getRequest();
        HttpServletResponse response = WebContextUtils.getResponse();
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("username"))) {
            session.setAttribute("username", "admin");
            session.setAttribute("password", "123456");
        } else {
            System.out.println("username======>" + session.getAttribute("username"));
            System.out.println("password======>" + session.getAttribute("password"));
        }
        System.out.println("端口：" + request.getServerPort() + "服务器的sessionId=====>" + session.getId());
        PageInfo pageInfo = userService.pageUser(userParam);
        setRequestAttribute("pageInfo", pageInfo);
        setRequestAttribute("userParam", userParam);
        return UserControllerMappingUrl.PAGE_LIST_RETURN;
    }

    /**
     * 查询用户详情信息
     * @param id     用户编号
     * @return
     */
    @GetMapping(UserControllerMappingUrl.ONE_USER)
    public String getDetail(@PathVariable(ID) Integer id) {
        User user = userService.getUser(id).orElseGet(User::new);
        setRequestAttribute("user", user);
        return UserControllerMappingUrl.PAGE_DETAIL_RETURN;
    }

    /**
     * 新增用户界面
     * @return
     */
    @GenerateToken
    @GetMapping(UserControllerMappingUrl.PAGE_ADD)
    public String addPage() {
        return UserControllerMappingUrl.PAGE_ADD_RETURN;
    }

    /**
     * 新增用戶信息
     s* @param user  用户对象
     * @return
     */
    @ValidateToken
    @PostMapping(UserControllerMappingUrl.USER)
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
    @GetMapping(UserControllerMappingUrl.PAGE_UPDATE)
    public String updatePage(Integer id) {
        User user = userService.getUser(id).orElseGet(User::new);
        setRequestAttribute("user", user);
        return UserControllerMappingUrl.PAGE_UPDATE_RETURN;
    }

    /**
     * 修改用户信息
     * @param user  用户对象
     * @return
     */
    @ValidateToken
    @PutMapping(UserControllerMappingUrl.ONE_USER)
    @ResponseBody
    public ResponseData update(User user) {
        ResponseData responseData = userService.updateUser(user);
        return responseData;
    }
}
