package com.wzu.appmanager.action;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class welAction {

    @RequestMapping("/")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value="/login")
    public String login(){

        return "welcome";
    }
}
