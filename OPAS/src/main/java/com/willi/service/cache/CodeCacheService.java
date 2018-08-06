package com.willi.service.cache;

import com.willi.entity.InvateCodeCacheEntity;
import org.hibernate.HibernateException;

public interface CodeCacheService {

    boolean addCache(InvateCodeCacheEntity codeCahcheEntity) throws HibernateException;

    boolean deleteCache(String createdUser) throws HibernateException;

    String getCache(String createdUser) throws HibernateException;
}
