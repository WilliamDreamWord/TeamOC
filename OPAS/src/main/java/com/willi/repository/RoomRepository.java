package com.willi.repository;

import com.willi.entity.UserRoomEntity;
import org.hibernate.HibernateException;

public interface RoomRepository {

    boolean addRoom(UserRoomEntity roomEntity) throws HibernateException;
}
