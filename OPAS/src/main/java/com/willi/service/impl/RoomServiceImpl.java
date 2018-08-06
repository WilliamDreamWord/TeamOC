package com.willi.service.impl;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.willi.entity.UserRoomEntity;
import com.willi.repository.impl.RoomRepositoryImpl;
import com.willi.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepositoryImpl roomRepository;

    @Override
    public boolean addRoom(UserRoomEntity roomEntity) throws HandlerException {

        return roomRepository.addRoom(roomEntity);
    }
}
