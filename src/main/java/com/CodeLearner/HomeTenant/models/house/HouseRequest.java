package com.CodeLearner.HomeTenant.models.house;

public class HouseRequest {
    private String code;
    private Integer numberOfRooms;
    private Double rent;
    private boolean isOccupied;
    private Long homeId;


    public HouseRequest() {
    }

    public HouseRequest(String code, Integer numberOfRooms, Double rent, boolean isOccupied) {
        this.code = code;
        this.numberOfRooms = numberOfRooms;
        this.rent = rent;
        this.isOccupied = isOccupied;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long homeId) {
        this.homeId = homeId;
    }
}
