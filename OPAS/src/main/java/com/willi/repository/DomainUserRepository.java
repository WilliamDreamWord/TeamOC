package com.willi.repository;

import com.willi.entity.UserBaseInformationEntity;
import org.hibernate.HibernateException;

import java.util.List;

public interface DomainUserRepository {

    //对User数据库的增删改查
    boolean add(UserBaseInformationEntity user) throws HibernateException;

    boolean deleteByuserName(String userName) throws HibernateException;

    boolean updateByuserName(String userName, String userPwd, String phoneNumber, String email) throws HibernateException;

    List<UserBaseInformationEntity> checkByuserName(String userName) throws HibernateException;

    List<UserBaseInformationEntity> checkAll() throws HibernateException;
}
