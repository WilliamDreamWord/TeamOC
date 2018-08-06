package com.willi.repository.impl;

import com.willi.entity.UserBaseInformationEntity;
import com.willi.repository.UserRepository;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    /**
     * @autor william
     * @Date 2018.5.16
     * 对数据库的增删改查操作
     */

    @Autowired
    private SessionFactory sessionFactory;

    private Transaction transaction = null;

    //初始化session
    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public boolean add(UserBaseInformationEntity user) throws HibernateException {
        try {
            Session session = getCurrentSession();
            session.save(user);
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
    public boolean deleteByuserName(String userName) throws HibernateException {
        try {
            Session session = getCurrentSession();
            String hql = "delete from UserBaseInformationEntity user where user.userName=:i";
            Query query = session.createQuery(hql);
            query.setString("i", userName);
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
    public boolean updateByuserName(String userName, String userPwd, String phoneNumber, String email) throws HibernateException {

        try {
            String hql = "update UserBaseInformationEntity user set user.userPwd=:userPwd , user.email=:email , user.phoneNum=:phoneNum where user.userName=:userName";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("userPwd", userPwd);
            query.setParameter("email", email);
            query.setParameter("phoneNum", phoneNumber);
            query.setParameter("userName", userName);
            query.executeUpdate();
            transaction = getCurrentSession().beginTransaction();
            transaction.commit();
            getCurrentSession().close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<UserBaseInformationEntity> checkByuserName(String userName) throws HibernateException {

        List<UserBaseInformationEntity> list;

        try {
            Session session = getCurrentSession();
            String hql = "from UserBaseInformationEntity user where user.userName=? order by user.userName desc";
            Query query = session.createQuery(hql);
            query.setString(0, userName);
            /*query.setString("n", userName);*/
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
    public List<UserBaseInformationEntity> checkAll() throws HibernateException {

        List<UserBaseInformationEntity> list;

        try {
            String hql = "from UserBaseInformationEntity order by userName desc ";
            Query query = getCurrentSession().createQuery(hql);
            list = query.list();
            getCurrentSession().close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    @Override
    public boolean findUser(String userName, String userPwd) throws HibernateException {

        List<UserBaseInformationEntity> list;

        try {
            String hql = "from UserBaseInformationEntity user where user.userName=:userName and user.userPwd=:userPwd";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("userName", userName);
            query.setParameter("userPwd", userPwd);
            list = query.list();
            getCurrentSession().close();
            //验证hql语句正确性
            /*System.out.println(list);*/
            if (list.toString().equals("[]"))
                return false;
            else
                return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findAcceptedUser(String userName) throws HibernateException {

        Session session = getCurrentSession();

        List<UserBaseInformationEntity> list;

        try {
            String hql = "from UserBaseInformationEntity user where user.userName=:userName";
            Query query = session.createQuery(hql);
            query.setParameter("userName", userName);
            list = query.list();
            session.close();
            if (list.toString().equals("[]"))
                return false;
            else
                return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
