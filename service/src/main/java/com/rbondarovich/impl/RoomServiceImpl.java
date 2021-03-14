package com.rbondarovich.impl;

import com.rbondarovich.bean.RoomBean;
import com.rbondarovich.entity.Room;
import com.rbondarovich.interfaces.RoomService;
import com.rbondarovich.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final EntityBeanConverterImpl converter;
    private final RoomRepository roomRepository;

    @Override
    public Iterable<RoomBean> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomBean> roomBeans = converter.convertToBeanList(rooms, RoomBean.class);

        return roomBeans;
    }

    @Override
    public RoomBean getRoomById(Long id) {
        Room room = roomRepository.getOne(id);
        RoomBean roomBean = converter.convertToBean(room, RoomBean.class);
        return roomBean;
    }

    @Override
    public void saveRoom(RoomBean roomBean) {
        Room room = converter.convertToEntity(roomBean, Room.class);
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
