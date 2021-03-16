package com.rbondarovich.interfaces;

import com.rbondarovich.bean.RoomBean;

import javax.servlet.http.HttpServletRequest;

public interface RoomService {

    Iterable<RoomBean> getAllRooms();

    RoomBean getRoomById (Long id);

    void saveRoom (RoomBean user);

    void deleteRoom (Long id);
}
