package com.example.JobPost;

public class JobCategory {

    private int id;
    private String category;

    public JobCategory(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public JobCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
