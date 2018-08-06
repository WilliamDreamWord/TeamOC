package com.willi.service.impl;

import com.willi.entity.UserBaseInformationEntity;
import com.willi.repository.UserRepository;
import com.willi.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    /**
     * @Autor william
     * @Date 2018.5.17
     * 服务层 - 用户注册
     */

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveUser(UserBaseInformationEntity user) {
        userRepository.add(user);
        return true;
    }

    @Override
    public List<UserBaseInformationEntity> lookUser(String userName) {
        return this.userRepository.checkByuserName(userName);
    }
}
