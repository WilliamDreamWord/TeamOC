package com.willi.service.opera_Record;

import org.hibernate.HibernateException;

public interface FindAccepterService {

    boolean findOut(String userName) throws HibernateException;
}
