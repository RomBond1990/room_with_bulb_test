package com.rbondarovich;

import com.rbondarovich.bean.RoomBean;
import com.rbondarovich.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping
    public ResponseEntity<Iterable<RoomBean>> getAllRooms() {
        Iterable<RoomBean> rooms = roomService.getAllRooms();
        ResponseEntity<Iterable<RoomBean>> responce = new ResponseEntity<>(rooms, HttpStatus.OK);

        return responce;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomBean> getRoomById (@PathVariable("roomId") Long roomId) {
        RoomBean room = roomService.getRoomById(roomId);
        ResponseEntity<RoomBean> result = new ResponseEntity<>(room, HttpStatus.OK);

        return result;
    }

    @PostMapping
    public ResponseEntity<RoomBean> saveRoom (@RequestBody RoomBean room) {
        roomService.saveRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Void> updateRoom (@PathVariable("roomId") @RequestBody RoomBean room) {
        roomService.saveRoom(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom (@PathVariable("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
