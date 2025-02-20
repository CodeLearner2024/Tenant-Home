package com.CodeLearner.HomeTenant.models.house;

import com.CodeLearner.HomeTenant.models.home.HomeResponse;

public class HouseResponse {
    private Long id;
    private String code;
    private Integer numberOfRooms;
    private Double rent;
    private boolean isOccupied;
    private HomeResponse home;

    public HouseResponse() {
    }

    public HouseResponse(Long id, String code, Integer numberOfRooms, Double rent, boolean isOccupied) {
        this.id = id;
        this.code = code;
        this.numberOfRooms = numberOfRooms;
        this.rent = rent;
        this.isOccupied = isOccupied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public HomeResponse getHome() {
        return home;
    }

    public void setHome(HomeResponse home) {
        this.home = home;
    }
}
