package org.example.backend.repository;

import org.example.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE " +
            "(:name IS NULL OR r.name LIKE %:name%) AND " +
            "(:address IS NULL OR r.address LIKE %:address%) AND " +
            "(:type IS NULL OR r.type = :type) AND " +
            "(:manager IS NULL OR r.manager LIKE %:manager%) AND " +
            "(:minShortPrice IS NULL OR r.shortPrice >= :minShortPrice) AND " +
            "(:maxShortPrice IS NULL OR r.shortPrice <= :maxShortPrice) AND " +
            "(:minMiddlePrice IS NULL OR r.middlePrice >= :minMiddlePrice) AND " +
            "(:maxMiddlePrice IS NULL OR r.middlePrice <= :maxMiddlePrice)")
    List<Room> filterRooms(
            @Param("name") String name,
            @Param("address") String address,
            @Param("type") String type,
            @Param("manager") String manager,
            @Param("minShortPrice") Double minShortPrice,
            @Param("maxShortPrice") Double maxShortPrice,
            @Param("minMiddlePrice") Double minMiddlePrice,
            @Param("maxMiddlePrice") Double maxMiddlePrice
    );
}
