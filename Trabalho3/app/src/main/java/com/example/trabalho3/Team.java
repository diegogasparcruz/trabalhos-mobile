package com.example.trabalho3;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Team implements Serializable {

    @Exclude
    private String key;
    private String name;
    private String star;
    private int titles;

    public Team() {}

    public Team(String name, String star, int titles) {
        this.name = name;
        this.star = star;
        this.titles = titles;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public int getTitles() {
        return titles;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }
}
