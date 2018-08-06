package com.willi.service.impl;

import com.willi.repository.UserRepository;
import com.willi.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean judgeCode(String checkCode_session, String checkCode) {

        if (checkCode_session == null || !checkCode_session.equals(checkCode)) {
            //验证码输入错误
            return false;
        } else
            return true;
    }

    @Override
    public boolean judgeUser(String userName, String userPwd) {
        if (userRepository.findUser(userName, userPwd)) {
            //登录成功
            return true;
        } else
            return false;
    }
}
