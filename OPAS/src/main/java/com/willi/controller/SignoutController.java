package com.willi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignoutController {

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        //退出登录返回登录界面
        return "logout success";
    }
}
