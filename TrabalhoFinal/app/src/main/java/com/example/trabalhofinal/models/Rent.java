package com.example.trabalhofinal.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Rent implements Serializable {
    private String id;
    private String image;
    private String title;
    private String description;
    private int type;
    private int numberBathrooms;
    private int numberBedrooms;
    private int numberResidents;
    private Double price;
    private Address address;
    private String userId;

    private Double latPoint;
    private Double lngPoint;

    public Rent() {
    }

    public Rent(String id, String image, String title, String description, int type, int numberBathrooms, int numberBedrooms, int numberResidents, Double price, Address address, String userId, Double latPoint, Double lngPoint) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.type = type;
        this.numberBathrooms = numberBathrooms;
        this.numberBedrooms = numberBedrooms;
        this.numberResidents = numberResidents;
        this.price = price;
        this.address = address;
        this.userId = userId;
        this.latPoint = latPoint;
        this.lngPoint = lngPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    public int getNumberResidents() {
        return numberResidents;
    }

    public void setNumberResidents(int numberResidents) {
        this.numberResidents = numberResidents;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addressId) {
        this.address = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getLatPoint() {
        return latPoint;
    }

    public void setLatPoint(Double latPoint) {
        this.latPoint = latPoint;
    }

    public Double getLngPoint() {
        return lngPoint;
    }

    public void setLngPoint(Double lngPoint) {
        this.lngPoint = lngPoint;
    }
}
