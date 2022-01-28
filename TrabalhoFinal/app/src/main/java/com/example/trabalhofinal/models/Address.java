package com.example.trabalhofinal.models;

import java.io.Serializable;

public class Address implements Serializable {
    private String cep;
    private String street;
    private String district;
    private String city;
    private String uf;
    private String complement;

    public Address() {}

    public Address(String cep, String street, String district, String city, String uf, String complement) {
        this.cep = cep;
        this.street = street;
        this.district = district;
        this.city = city;
        this.uf = uf;
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
