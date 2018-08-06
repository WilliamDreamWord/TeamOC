package com.willi.service.register;

import com.willi.entity.UserBaseInformationEntity;

import java.util.List;

public interface RegisterService {

    public boolean saveUser(UserBaseInformationEntity user);

    public List<UserBaseInformationEntity> lookUser(String userName);
}
