package com.willi.service.impl;

import com.willi.entity.UserInvateCodeEntity;
import com.willi.repository.impl.CodeResitoryImpl;
import com.willi.service.opera_Record.CodeService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

    /**
     * 将邀请码以及属性值保存至数据库
     * 创建者创建邀请码是现保存至数据库中
     * 如果有接受者的话再将接受者属性值写入数据库
     */

    @Autowired
    private CodeResitoryImpl codeResitory;

    @Override
    public boolean createCode(UserInvateCodeEntity codeEntity) throws HibernateException {

        return codeResitory.addCode(codeEntity);
    }

    @Override
    public boolean acceptCode(String acceptedUser, String acceptedDate, String code) throws HibernateException {

        return codeResitory.updateCode(acceptedUser, acceptedDate, code);
    }

    @Override
    public boolean deleteCode(String createdUser) throws HibernateException {

        return codeResitory.deleteCode(createdUser);
    }

}
