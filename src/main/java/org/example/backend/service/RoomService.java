package org.example.backend.service;

import org.example.backend.entity.Room;

import java.util.List;

public interface RoomService {
    public List<Room> getRooms();
    public Room getRoom(int id);
    public Room saveRoom(Room room);
    public void deleteRoom(int id);
    public long getRoomsCount();
    public List<Room> filterRooms(String name, String address, String type, String manager,
                                  Double minShortPrice, Double maxShortPrice,
                                  Double minMiddlePrice, Double maxMiddlePrice);
}
