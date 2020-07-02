package com.wzu.appmanager.action;


import com.wzu.appmanager.pojo.DevUser;

import com.wzu.appmanager.service.developer.DevUserService;
import com.wzu.appmanager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/dev")
public class DevAction {

    @Autowired
    private DevUserService devUserService;
    @RequestMapping(value="/login")
    public String login(){
        return "/dev/devlogin";
    }
    @RequestMapping(value="/dologin",method= RequestMethod.POST)
    public String doLogin(Model model, @RequestParam String userCode, @RequestParam String userPassword, HttpSession session){

        //调用service方法，进行用户匹配
        DevUser user = null;
        try {
            user = devUserService.login(userCode,userPassword);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (user.getState() ==0)
        {
            model.addAttribute("error","该账号还未激活");
            return "/dev/devlogin";
        }
        if(null != user && user.getState()==1){//登录成功
            //放入session
            session.setAttribute("login_type","common");
            session.setAttribute(Constants.USER_SESSION, user);
            session.setAttribute("login_user",user);
            //页面跳转（main.jsp）
            return "devindex";
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
            model.addAttribute("error", "用户名或密码不正确");
            return "/dev/devlogin";
        }


    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("login_type");
        session.removeAttribute(Constants.USER_SESSION);
        session.removeAttribute("login_user");
        return "redirect:/login";
    }

    //导航至注册页
    @RequestMapping(value = "/register")
    public String register(){
        return "/dev/register";
    }
    @RequestMapping(value = "/doregister" ,method= RequestMethod.POST)
    public String doregister(DevUser user,Model model){
        if(devUserService .doRegister(user.getDevcode(),user.getDevpassword(),user.getDevemail(),user.getDevname(),user.getDevinfo())){
            model.addAttribute("msg", "注册成功，请登录邮箱激活账号");
        }else{
            model.addAttribute("msg", "注册失败，请检查相关信息");
        }
        return "/dev/result";
    }
    @RequestMapping(value = "/activity")
    public String activity(@RequestParam(value = "code") String code){
        devUserService.activeUser(code);
        return "/dev/devlogin";
    }
}
