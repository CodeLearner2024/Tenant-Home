package com.CodeLearner.HomeTenant.models.house;

import com.CodeLearner.HomeTenant.models.home.Home;
import com.CodeLearner.HomeTenant.models.leaseagreement.LeaseAgreement;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "House")
@Table(name = "tbl_houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Integer numberOfRooms;
    private Double rent;
    private boolean isOccupied;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Home home;

    @OneToMany(mappedBy = "house")
    @JsonManagedReference
    private List<LeaseAgreement> leaseAgreements;

    public House() {
    }

    public House(Long id, String code, Integer numberOfRooms, Double rent, boolean isOccupied) {
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

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
