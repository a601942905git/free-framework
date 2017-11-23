package com.free.framework.core.login.controller;

import com.free.framework.core.login.param.LoginParam;
import com.free.framework.core.login.service.LoginService;
import com.free.framework.mq.sender.ActiveMQSender;
import com.free.framework.plateform.common.response.ResponseData;
import com.free.framework.plateform.constant.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * com.free.framework.core.login.LoginController
 *
 * @author lipeng
 * @dateTime 2017/9/9 10:54
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ActiveMQSender activeMQSender;

    @Value("${spring.activemq.admin.destination}")
    private String destination;

    /**
     * 请求登陆页面
     * @return  登陆页面
     */
    @GetMapping(LoginControllerMappingUrl.LOGIN_CONTROLLER)
    public String loginPage() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("text", "测试");
        activeMQSender.send(destination, map);
        return LoginControllerMappingUrl.LOGIN_RETURN_PAGE;
    }

    /**
     * 登陆
     * @param loginParam    登陆信息
     * @return  是否登陆成功
     */
    @PostMapping(LoginControllerMappingUrl.LOGIN)
    @ResponseBody
    public ResponseData login(LoginParam loginParam) {
        ResponseData responseData = loginService.login(loginParam);
        return responseData;
    }

    /**
     * 退出登陆
     * @param request   request对象
     * @return          登陆页面
     */
    @GetMapping(LoginControllerMappingUrl.LOG_OUT)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return SystemConstants.REDIRECT + LoginControllerMappingUrl.LOGIN_CONTROLLER;
    }

    /*@GetMapping(value="/login")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(User user, RedirectAttributes redirectAttributes){

        String username = user.getLoginCode();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginCode(), user.getLoginPassword());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "redirect:/user";
        }else{
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        log.info("------没有权限-------");
        return "403";
    }*/
}