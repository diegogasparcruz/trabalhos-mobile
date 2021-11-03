package com.example.trabalho2;

public class Team {
    private Integer id;
    private String name;
    private String starOfTeam;
    private String numberTitles;

    public Team(Integer id, String name, String starOfTeam, String numberTitles) {
        this.id = id;
        this.name = name;
        this.starOfTeam = starOfTeam;
        this.numberTitles = numberTitles;
    }

    public Team() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarOfTeam() {
        return starOfTeam;
    }

    public void setStarOfTeam(String starOfTeam) {
        this.starOfTeam = starOfTeam;
    }

    public String getNumberTitles() {
        return numberTitles;
    }

    public void setNumberTitles(String numberTitles) {
        this.numberTitles = numberTitles;
    }
}
