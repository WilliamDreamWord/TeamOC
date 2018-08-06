package com.willi.repository.impl;

import com.willi.entity.UserRoomEntity;
import com.willi.repository.RoomRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Transaction transaction;

    //初始化session
    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public boolean addRoom(UserRoomEntity roomEntity) throws HibernateException {

        Session session = getCurrentSession();

        try {
            session.save(roomEntity);
            transaction = session.beginTransaction();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
