package com.willi.controller;

import com.willi.entity.UserBaseInformationEntity;
import com.willi.service.info_Manage.ManageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Autor William
 * @Date 2018/5/22
 * 用户通过该controller完成主界面上的所有操作（编辑自身信息，修改头像，退出登录，创建房间，系统消息）
 */
@Controller
public class UpdateInfoContoller {

    @Autowired
    private ManageInfoService manageService;

    //用户编辑基础信息功能
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @ResponseBody
    public List<UserBaseInformationEntity> Update(String password, String phoneNum, String email) {


        String userName = "admin";

        if (manageService.editUserInf(userName, password, phoneNum, email)) {
            return manageService.refresh(userName);
        } else {
            return null;
        }
    }
}
