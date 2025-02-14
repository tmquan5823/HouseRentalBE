package org.example.backend.controller;

import org.example.backend.entity.Room;
import org.example.backend.service.CloudinaryService;
import org.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public RoomController(RoomService roomService, CloudinaryService cloudinaryService) {
        this.roomService = roomService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Room>> filterRooms(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String manager,
            @RequestParam(required = false) String shortPrice,
            @RequestParam(required = false) String middlePrice) {

        Double minShortPrice = null, maxShortPrice = null;
        if (shortPrice != null && shortPrice.contains(",")) {
            String[] prices = shortPrice.split(",");
            minShortPrice = Double.parseDouble(prices[0]);
            maxShortPrice = Double.parseDouble(prices[1]);
        }

        Double minMiddlePrice = null, maxMiddlePrice = null;
        if (middlePrice != null && middlePrice.contains(",")) {
            String[] prices = middlePrice.split(",");
            minMiddlePrice = Double.parseDouble(prices[0]);
            maxMiddlePrice = Double.parseDouble(prices[1]);
        }

        List<Room> filteredRooms = roomService.filterRooms(name, address, type, manager, minShortPrice, maxShortPrice, minMiddlePrice, maxMiddlePrice);
        Collections.reverse(filteredRooms);

        return ResponseEntity.ok(filteredRooms);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        List<Room> rooms = roomService.getRooms();
        Collections.reverse(rooms);
        return ResponseEntity.ok(rooms);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable int id) {
        return ResponseEntity.ok(roomService.getRoom(id));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("shortPrice") double shortPrice,
            @RequestParam("middlePrice") double middlePrice,
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            @RequestParam("manager") String manager) {

        try {
            String imageUrl = cloudinaryService.uploadFile(file);

            Room room = new Room();
            room.setName(name);
            room.setAddress(address);
            room.setRoomNumber(roomNumber);
            room.setShortPrice(shortPrice);
            room.setMiddlePrice(middlePrice);
            room.setType(type);
            room.setStatus(status);
            room.setImageUrl(imageUrl);
            room.setManager(manager);

            return ResponseEntity.ok(roomService.saveRoom(room));

        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "File upload failed"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("address") String address,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("shortPrice") double shortPrice,
            @RequestParam("name") String name,
            @RequestParam("middlePrice") double middlePrice,
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            @RequestParam("manager") String manager) {

        Room existingRoom = roomService.getRoom(id);

        try {
            if (file != null && !file.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(file);
                existingRoom.setImageUrl(imageUrl);
            }

            existingRoom.setName(name);
            existingRoom.setAddress(address);
            existingRoom.setRoomNumber(roomNumber);
            existingRoom.setShortPrice(shortPrice);
            existingRoom.setMiddlePrice(middlePrice);
            existingRoom.setType(type);
            existingRoom.setStatus(status);
            existingRoom.setManager(manager);

            return ResponseEntity.ok(roomService.saveRoom(existingRoom));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "File upload failed"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable int id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(Map.of("message", "Room deleted successfully"));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getRoomsCount() {
        return ResponseEntity.ok(Map.of("count", roomService.getRoomsCount()));
    }

}
