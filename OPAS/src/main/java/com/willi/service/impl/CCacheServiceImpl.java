package com.willi.service.impl;

import com.willi.entity.InvateCodeCacheEntity;
import com.willi.repository.impl.CodeCacheResitoryImpl;
import com.willi.service.cache.CodeCacheService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CCacheServiceImpl implements CodeCacheService {

    @Autowired
    private CodeCacheResitoryImpl cacheResitory;

    @Override
    public boolean addCache(InvateCodeCacheEntity codeCahcheEntity) throws HibernateException {

        return cacheResitory.add(codeCahcheEntity);
    }

    @Override
    public boolean deleteCache(String createdUser) throws HibernateException {

        return cacheResitory.delete(createdUser);
    }

    @Override
    public String getCache(String createdUser) throws HibernateException {

        return cacheResitory.getCode(createdUser);
    }
}
