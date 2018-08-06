package com.willi.repository.impl;

import com.willi.entity.UserInvateCodeEntity;
import com.willi.repository.CodeResitory;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CodeResitoryImpl implements CodeResitory {

    @Autowired
    private SessionFactory sessionFactory;

    private Transaction transaction;

    //初始化session
    private Session getCurrentSession() {

        return this.sessionFactory.openSession();
    }

    @Override
    public boolean addCode(UserInvateCodeEntity codeEntity) {

        Session session = getCurrentSession();

        try {
            session.save(codeEntity);
            transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public List<UserInvateCodeEntity> lookAllCode() {

        List<UserInvateCodeEntity> list;
        Session session = getCurrentSession();

        try {
            String hql = "from UserInvateCodeEntity order by createdUser desc ";
            Query query = session.createQuery(hql);
            list = query.list();
            transaction = session.beginTransaction();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    @Override
    public List<UserInvateCodeEntity> findCode(String createdUser) {

        List<UserInvateCodeEntity> list;
        Session session = getCurrentSession();

        try {
            String hql = "from UserInvateCodeEntity code where code.createdUser=? order by code.createdUser desc ";
            Query query = session.createQuery(hql);
            query.setString(0, createdUser);
            list = query.list();
            transaction = session.beginTransaction();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return list;

    }

    @Override
    public boolean deleteAll() {

        Session session = getCurrentSession();

        try {
            String hql = "delete Testcasebpel";
            Query query = session.createQuery(hql);
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
    public boolean deleteCode(String createdUser) {

        Session session = getCurrentSession();

        try {
            String hql = "delete from UserInvateCodeEntity code where code.createdUser=:i";
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
    public boolean updateCode(String acceptedUser, String acceptedDate, String code) {

        //该版本号作为当前操作的标识，初始值为2，表示完成第一次接受邀请码后版本为2
        int numVersion;

        //获取数据库中code标识的当前版本号
        int version = getVersion(code);

        Session session = getCurrentSession();


        try {

            switch (version) {
                case 0:
                    System.out.println(" error /// created code failed！！");
                    break;

                case 1:

                    numVersion = 2;
                    String hql1 = "update UserInvateCodeEntity code set code.numVersion=:numVersion, code.acceptedUser1=:acceptedUser, code.acceptedDate1=:acceptedDate where code.code=:code";
                    Query query = session.createQuery(hql1);
                    query.setParameter("numVersion", numVersion);
                    query.setParameter("acceptedUser", acceptedUser);
                    query.setParameter("acceptedDate", acceptedDate);
                    query.setParameter("code", code);
                    query.executeUpdate();
                    transaction = session.beginTransaction();
                    transaction.commit();
                    session.close();

                    break;

                case 2:

                    numVersion = 3;
                    String hql2 = "update UserInvateCodeEntity code set code.numVersion=:numVersion, code.acceptedUser2=:acceptedUser, code.acceptedDate2=:acceptedDate where code.code=:code";
                    Query query1 = session.createQuery(hql2);
                    query1.setParameter("numVersion", numVersion);
                    query1.setParameter("acceptedUser", acceptedUser);
                    query1.setParameter("acceptedDate", acceptedDate);
                    query1.setParameter("code", code);
                    query1.executeUpdate();
                    transaction = session.beginTransaction();
                    transaction.commit();
                    session.close();
                    break;

                case 3:

                    numVersion = 4;
                    String hql3 = "update UserInvateCodeEntity code set code.numVersion=:numVersion, code.acceptedUser3=:acceptedUser, code.acceptedDate3=:acceptedDate where code.code=:code";
                    Query query2 = session.createQuery(hql3);
                    query2.setParameter("numVersion", numVersion);
                    query2.setParameter("acceptedUser", acceptedUser);
                    query2.setParameter("acceptedDate", acceptedDate);
                    query2.setParameter("code", code);
                    query2.executeUpdate();
                    transaction = session.beginTransaction();
                    transaction.commit();
                    session.close();
                    break;

                case 4:

                    numVersion = 5;
                    String hql4 = "update UserInvateCodeEntity code set code.numVersion=:numVersion, code.acceptedUser4=:acceptedUser, code.acceptedDate4=:acceptedDate where code.code=:code";
                    Query query3 = session.createQuery(hql4);
                    query3.setParameter("numVersion", numVersion);
                    query3.setParameter("acceptedUser", acceptedUser);
                    query3.setParameter("acceptedDate", acceptedDate);
                    query3.setParameter("code", code);
                    query3.executeUpdate();
                    transaction = session.beginTransaction();
                    transaction.commit();
                    session.close();
                    break;

                case 5:

                    numVersion = 6;
                    String hql5 = "update UserInvateCodeEntity code set code.numVersion=:numVersion, code.acceptedUser5=:acceptedUser, code.acceptedDate5=:acceptedDate where code.code=:code";
                    Query query4 = session.createQuery(hql5);
                    query4.setParameter("numVersion", numVersion);
                    query4.setParameter("acceptedUser", acceptedUser);
                    query4.setParameter("acceptedDate", acceptedDate);
                    query4.setParameter("code", code);
                    query4.executeUpdate();
                    transaction = session.beginTransaction();
                    transaction.commit();
                    session.close();
                    break;

                case 6:

                    System.out.println("error /// room is full !!! ");
                    break;
            }


        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    @Override
    public String getCode(String createdUser) {

        Session session = getCurrentSession();

        int version = 1;
        String gettedCode;

        try {
            String hql = "select code1.code from UserInvateCodeEntity code1 where code1.createdUser=:createdUser and code1.numVersion=:version";
            Query query = session.createQuery(hql);
            query.setParameter("createdUser", createdUser);
            query.setParameter("version", version);
            transaction = session.beginTransaction();
            transaction.commit();
            gettedCode = (String) query.uniqueResult();
            session.close();

            return gettedCode;
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getNum(String code) {

        Session session = getCurrentSession();

        int num;

        try {
            String hql = "select numVersion from UserInvateCodeEntity code where code.code=:code";
            Query query = session.createQuery(hql);
            query.setParameter("code", code);
            transaction = session.beginTransaction();
            transaction.commit();
            num = (int) query.uniqueResult();
            session.close();

            return num;
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public int getVersion(String code) {

        Session session = getCurrentSession();

        int version;

        try {
            String hql = "select numVersion from UserInvateCodeEntity code where code.code=:code";
            Query query = session.createQuery(hql);
            query.setParameter("code", code);
            transaction = session.beginTransaction();
            transaction.commit();
            version = (int) query.uniqueResult();
            session.close();

            return version;

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
