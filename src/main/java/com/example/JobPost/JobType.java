package com.example.JobPost;

public class JobType {

    private int id;
    private String jobType;

    public JobType(int id, String jobType) {
        this.id = id;
        this.jobType = jobType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
