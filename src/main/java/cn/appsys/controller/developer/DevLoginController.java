package cn.appsys.controller.developer;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/dev")
public class DevLoginController {

    @Resource
    private DevUserService devUserService;

    //登陆进入jsp页面
    @RequestMapping(value="/login")
    public String login(){
        return "devlogin";
    }

    // 开发者登陆验证
    @RequestMapping(value = "/dologin")
    public String doLogin(String devCode, String devPassword, HttpServletRequest request, HttpSession session){
        DevUser devUser = null;
        try {
            devUser = devUserService.login(devCode,devPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (devUser != null){
            session.setAttribute(Constants.DEV_USER_SESSION, devUser);
            return "redirect:/dev/flatform/main";
        }else {
            request.setAttribute("error", "用户名或密码不正确");
            return "devlogin";
        }
    }

    //登陆成功进入主页面
    @RequestMapping(value = "/flatform/main")
    public String main(HttpSession session){
        if (session.getAttribute(Constants.DEV_USER_SESSION) == null){
            return "redirect:/dev/login";
        }
        return "developer/main";
    }

    // 注销登陆
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        //清除session
        session.removeAttribute(Constants.DEV_USER_SESSION);
        return "devlogin";
    }
}
