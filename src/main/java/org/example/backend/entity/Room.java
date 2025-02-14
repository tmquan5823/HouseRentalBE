package org.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String type;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Double shortPrice;

    @Column(nullable = false)
    private Double middlePrice;

    @Column
    private String manager;

    @Column(nullable = false)
    private String status;

    public Room() {}

    public Room(String address, String imageUrl, String name, Double shortPrice, Double middlePrice, String roomNumber) {
        this.address = address;
        this.imageUrl = imageUrl;
        this.name = name;
        this.shortPrice = shortPrice;
        this.middlePrice = middlePrice;
        this.roomNumber = roomNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getShortPrice() {
        return shortPrice;
    }

    public void setShortPrice(Double shortPrice) {
        this.shortPrice = shortPrice;
    }

    public Double getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(Double middlePrice) {
        this.middlePrice = middlePrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
