package com.example.adminmanagment.model;

import com.example.adminmanagment.web.ServletAdminUpdateServlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {

    private int id;
    private String name;
    private String address;
    private String email;
    private String password;
    private int role;

    public Admin(int id, String name, String address, String email, String password, int role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Admin(String name, String address, String email, String password, int role) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Admin(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
