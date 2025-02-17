package com.CodeLearner.HomeTenant.home;

import jakarta.persistence.*;

@Entity(name = "Home")
@Table(name = "tbl_homes")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String adresse;


    public Home() {
    }

    public Home(Long id, String code, String name, String adresse) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.adresse = adresse;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
