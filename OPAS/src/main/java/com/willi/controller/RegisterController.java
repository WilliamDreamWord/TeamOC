package com.willi.controller;

import com.willi.entity.UserBaseInformationEntity;
import com.willi.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Autor William
 * @Date 2018/5/21
 * @Return List
 * 用户通过该controller完成注册
 */

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    @ResponseBody
    public List<UserBaseInformationEntity> register(String userName, String userPwd, String phone, String email) {


        UserBaseInformationEntity user = new UserBaseInformationEntity();
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        user.setPhoneNum(phone);
        user.setEmail(email);

        if (registerService.saveUser(user)) {
            return registerService.lookUser(userName);
        } else {
            return null;
        }
    }

}
