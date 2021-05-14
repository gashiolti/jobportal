package com.example.JobPost;

public class Salary {

    private int id, jobPostID;
    private String salary;

    public Salary(int id, int jobPostID, String salary) {
        this.id = id;
        this.jobPostID = jobPostID;
        this.salary = salary;
    }

    public Salary(int jobPostID, String salary) {
        this.jobPostID = jobPostID;
        this.salary = salary;
    }

    public Salary(String salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobPostID() {
        return jobPostID;
    }

    public void setJobPostID(int jobPostID) {
        this.jobPostID = jobPostID;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", jobPostID=" + jobPostID +
                ", salary='" + salary + '\'' +
                '}';
    }
}
