package com.example.companymanagment;

public class Company {

    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNr;
    private String webUrl;
    private String description;
    private String pass;
    private int role;

    public Company(int id, String name, String address, String email, String phoneNr, String webUrl,
                   String descriptionString, String pass, int role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNr = phoneNr;
        this.webUrl = webUrl;
        this.description = descriptionString;
        this.pass = pass;
        this.role = role;
    }

    public Company(String name, String address, String email, String phoneNr, String webUrl, String description,
                   String pass, int role) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNr = phoneNr;
        this.webUrl = webUrl;
        this.description = description;
        this.pass = pass;
        this.role = role;
    }

    public Company(int id, String name, String address, String email, String pass, String webUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.pass = pass;
        this.webUrl = webUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", pass='" + pass + '\'' +
                ", role=" + role +
                '}';
    }
}
