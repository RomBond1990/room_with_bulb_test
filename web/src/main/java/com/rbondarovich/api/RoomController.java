package com.rbondarovich.api;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.rbondarovich.bean.RoomBean;
import com.rbondarovich.exception.WrongRoomException;
import com.rbondarovich.impl.RoomServiceImpl;
import com.rbondarovich.utils.LocationFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;
    private final LocationFinder locationFinder;

    @GetMapping
    public ResponseEntity<Iterable<RoomBean>> getAllRooms() {
        Iterable<RoomBean> rooms = roomService.getAllRooms();
        ResponseEntity<Iterable<RoomBean>> responce = new ResponseEntity<>(rooms, HttpStatus.OK);

        return responce;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomBean> getRoomById(@PathVariable("roomId") Long roomId, HttpServletRequest request) throws IOException, GeoIp2Exception {
        RoomBean room = roomService.getRoomById(roomId);
        if (!checkingAccessToRoom(room, request)) {
            throw new WrongRoomException("You can't enter the room which place in another country");
        }
        ResponseEntity<RoomBean> result = new ResponseEntity<>(room, HttpStatus.OK);

        return result;
    }

    @PostMapping
    public ResponseEntity<RoomBean> saveRoom(@RequestBody RoomBean room) {
        roomService.saveRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Void> updateRoom(@PathVariable("roomId") Long roomId, @RequestBody RoomBean room) {
        RoomBean roomFromDb = roomService.getRoomById(roomId);
        roomFromDb.setName(room.getName());
        roomFromDb.setCountry(room.getCountry());
        roomFromDb.setBulb(room.getBulb());
        roomService.saveRoom(roomFromDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Boolean checkingAccessToRoom(RoomBean room, HttpServletRequest request) throws IOException, GeoIp2Exception {
        Boolean result = false;
//        String ip = locationFinder.getRemoteIpFrom(request);
        Set<String> countryNames = locationFinder.getCountryByIp("37.214.49.20");
        for (String countryName : countryNames) {
            if (countryName.equals(room.getCountry())) result = true;
        }
        return result;
    }
}
