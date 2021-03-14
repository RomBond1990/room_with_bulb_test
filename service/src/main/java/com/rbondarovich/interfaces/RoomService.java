package com.rbondarovich.interfaces;

import com.rbondarovich.bean.RoomBean;

public interface RoomService {

    Iterable<RoomBean> getAllRooms();

    RoomBean getRoomById (Long id);

    void saveRoom (RoomBean user);

    void deleteRoom (Long id);
}
