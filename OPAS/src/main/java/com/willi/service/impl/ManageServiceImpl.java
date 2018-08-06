package com.willi.service.impl;

import com.willi.entity.UserBaseInformationEntity;
import com.willi.repository.UserRepository;
import com.willi.service.info_Manage.ManageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageInfoService {

    @Autowired
    private UserRepository userRepository;

    //用户编辑信息时，默认为根据userName来更新
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public boolean editUserInf(String userName, String password, String phoneNum, String email) {
        if (userRepository.updateByuserName(userName, password, phoneNum, email))
            return true;
        else
            return false;
    }

    //当用户编辑修改完信息之后，点击保存调用此服务方法
    @Override
    public List<UserBaseInformationEntity> refresh(String userName) {
        return userRepository.checkByuserName(userName);
    }
}
