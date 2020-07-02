package com.wzu.appmanager.action;


import com.wzu.appmanager.pojo.BackendUser;
import com.wzu.appmanager.service.backend.BackendUserService;
import com.wzu.appmanager.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/manager")
public class ManagerAction {
    private Logger logger = Logger.getLogger(ManagerAction.class);

    @Resource
    private BackendUserService backendUserService;



    @RequestMapping(value="/login")
    public String login(){
        return "backendlogin";
    }

    @RequestMapping(value="/dologin",method= RequestMethod.POST)
    public String doLogin(Model model, @RequestParam String userCode, @RequestParam String userPassword, HttpSession session){

        //调用service方法，进行用户匹配
        BackendUser user = null;
        try {
            user = backendUserService.login(userCode,userPassword);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(null != user){//登录成功
            //放入session
            session.setAttribute("login_type","admin");
            session.setAttribute(Constants.USER_SESSION, user);
            session.setAttribute("login_user",user);
            //页面跳转（main.jsp）
            return "index";
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
           model.addAttribute("error", "用户名或密码不正确");
            return "backendlogin";
        }
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("login_type");
        session.removeAttribute(Constants.USER_SESSION);
        session.removeAttribute("login_user");
        return "redirect:/login";
    }
}
