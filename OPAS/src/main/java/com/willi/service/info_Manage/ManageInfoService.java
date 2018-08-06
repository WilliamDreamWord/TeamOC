package com.willi.service.info_Manage;

import com.willi.entity.UserBaseInformationEntity;

import java.util.List;

public interface ManageInfoService {

    public boolean editUserInf(String userName, String password, String phoneNum, String email);

    public List<UserBaseInformationEntity> refresh(String userName);
}
