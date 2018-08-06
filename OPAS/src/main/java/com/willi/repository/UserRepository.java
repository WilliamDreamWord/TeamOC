package com.willi.repository;

import org.hibernate.HibernateException;

public interface UserRepository extends DomainUserRepository {

    boolean findUser(String userName, String userPwd) throws HibernateException;

    boolean findAcceptedUser(String userName) throws HibernateException;
}
