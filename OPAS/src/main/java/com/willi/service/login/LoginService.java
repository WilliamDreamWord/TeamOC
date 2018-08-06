package com.willi.service.login;

public interface LoginService {

    public boolean judgeCode(String checkCode_session, String checkCode);

    public boolean judgeUser(String userName, String userPwd);

}
