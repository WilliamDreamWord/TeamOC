package com.willi.service.opera_Record;

import com.willi.entity.UserInvateCodeEntity;
import org.hibernate.HibernateException;

public interface CodeService {

    boolean createCode(UserInvateCodeEntity codeEntity) throws HibernateException;

    boolean acceptCode(String acceptedUser, String acceptedDate, String code) throws HibernateException;

    boolean deleteCode(String createdUser) throws HibernateException;
}
