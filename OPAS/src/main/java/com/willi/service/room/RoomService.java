package com.willi.service.room;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.willi.entity.UserRoomEntity;

public interface RoomService {

    boolean addRoom(UserRoomEntity roomEntity) throws HandlerException;
}
