package com.example.responsibilityhome.Model;

import java.io.Serializable;

public class Trade implements Serializable {
    private String name;
    private String location;
    private String landlordId ;
    private String renterId ;

    public Trade(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Trade(String name, String location, String landlordId, String renterId) {
        this.name = name;
        this.location = location;
        this.landlordId = landlordId;
        this.renterId = renterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(String landlordId) {
        this.landlordId = landlordId;
    }

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }
}
