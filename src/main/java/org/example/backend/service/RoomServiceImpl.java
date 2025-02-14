package org.example.backend.service;

import org.example.backend.entity.Room;
import org.example.backend.expection.RoomNotFoundException;
import org.example.backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public long getRoomsCount() {
        return roomRepository.count();
    }

    @Override
    public void deleteRoom(int id) {
        if (!roomRepository.existsById(id)) {
            throw new RoomNotFoundException("Room with ID " + id + " not found");
        }
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> filterRooms(String name, String address, String type, String manager,
                                  Double minShortPrice, Double maxShortPrice,
                                  Double minMiddlePrice, Double maxMiddlePrice) {
        return roomRepository.filterRooms(name, address, type, manager, minShortPrice, maxShortPrice, minMiddlePrice, maxMiddlePrice);
    }
}
