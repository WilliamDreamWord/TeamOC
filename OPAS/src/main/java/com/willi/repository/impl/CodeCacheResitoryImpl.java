package com.willi.repository.impl;

import com.willi.entity.InvateCodeCacheEntity;
import com.willi.repository.CodeCacheResitory;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CodeCacheResitoryImpl implements CodeCacheResitory {

    @Autowired
    private SessionFactory sessionFactory;

    private Transaction transaction;

    //初始化session
    private Session getCurrentSession() {

        return this.sessionFactory.openSession();
    }

    @Override
    public boolean add(InvateCodeCacheEntity codeCahcheEntity) {

        Session session = getCurrentSession();

        try {
            session.save(codeCahcheEntity);
            transaction = session.beginTransaction();
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String createdUser) {

        Session session = getCurrentSession();

        try {
            String hql = "delete from InvateCodeCacheEntity cache where cache.createdUser=:i";
            Query query = session.createQuery(hql);
            query.setString("i", createdUser);
            query.executeUpdate();
            transaction = session.beginTransaction();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public String getCode(String createdUser) {

        Session session = getCurrentSession();

        String code;

        try {
            String hql = "select cache.code from InvateCodeCacheEntity cache where cache.createdUser=:createdUser";
            Query query = session.createQuery(hql);
            query.setParameter("createdUser", createdUser);
            transaction = session.beginTransaction();
            transaction.commit();
            code = (String) query.uniqueResult();
            session.close();
            return code;
        } catch (HibernateException e) {
            e.printStackTrace();

            return null;
        }
    }
}
