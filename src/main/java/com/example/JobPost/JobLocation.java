package com.example.JobPost;

public class JobLocation {

    private int id;
    private String city, country;

    public JobLocation(int id, String city, String country) {
        this.id = id;
        this.city = city;
        this.country = country;
    }

    public JobLocation(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
