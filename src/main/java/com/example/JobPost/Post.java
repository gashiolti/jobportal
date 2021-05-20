package com.example.JobPost;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Post {

    private int id, jobTypeID, jobCategory, companyID, jobLocation, vacancy;
    private LocalDate posted, expires;
    private String title, description, companyDesc, companyEmail, companyWeb, salary;

    //toString
    private String jobType, category, companyName, location;
    private InputStream logo;


    public Post(int id, int jobTypeID, int jobCategory, int companyID, int jobLocation, LocalDate posted,
                LocalDate expires, String title, String description, int vacancy) {
        this.id = id;
        this.jobTypeID = jobTypeID;
        this.jobCategory = jobCategory;
        this.companyID = companyID;
        this.jobLocation = jobLocation;
        this.posted = posted;
        this.expires = expires;
        this.title = title;
        this.description = description;
        this.vacancy = vacancy;
    }

    public Post(int jobTypeID, int jobCategory, int companyID, int jobLocation, LocalDate posted, LocalDate expires,
                String title, String description, int vacancy) {
        this.jobTypeID = jobTypeID;
        this.jobCategory = jobCategory;
        this.companyID = companyID;
        this.jobLocation = jobLocation;
        this.posted = posted;
        this.expires = expires;
        this.title = title;
        this.description = description;
        this.vacancy = vacancy;
    }

    //constructor to display jobdetails by id
    public Post(String jobType, String category, String companyName, String location, LocalDate posted,
                LocalDate expires, String title, String description, int vacancy, InputStream logo, String companyDesc,
                String companyEmail, String companyWeb) {
        this.jobType = jobType;
        this.category = category;
        this.companyName = companyName;
        this.location = location;
        this.posted = posted;
        this.expires = expires;
        this.title = title;
        this.description = description;
        this.vacancy = vacancy;
        this.logo = logo;
        this.companyDesc = companyDesc;
        this.companyEmail = companyEmail;
        this.companyWeb = companyWeb;
    }

    // constructor to display POST data in admin panel
    public Post(int id, String jobType, String category, String companyName, String location, LocalDate posted,
                LocalDate expires, String title, int vacancy) {
        this.id = id;
        this.jobType = jobType;
        this.category = category;
        this.companyName = companyName;
        this.location = location;
        this.posted = posted;
        this.expires = expires;
        this.title = title;
        this.vacancy = vacancy;
    }

    public Post(String jobType, String category, String companyName, String location, LocalDate posted,
                LocalDate expires, String title, int vacancy) {
        this.jobType = jobType;
        this.category = category;
        this.companyName = companyName;
        this.location = location;
        this.posted = posted;
        this.expires = expires;
        this.title = title;
        this.vacancy = vacancy;
    }

    //constructor to display posts on joblist file
    public Post(int postID, int jobTypeID, int jobCategory, int jobLocation, String jobType, String companyName,
                String location, LocalDate posted, LocalDate expires, String title, String salary) {
        this.id = postID;
        this.jobTypeID = jobTypeID;
        this.jobCategory = jobCategory;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.companyName = companyName;
        this.location = location;
        this.posted = posted;
        this.expires = expires;
        this.title = title;
        this.salary = salary;
    }

    //constructor to update post from user
    public Post(int jobType, int category, LocalDate expires, String title, String description, int vacancy) {
        this.jobTypeID = jobType;
        this.jobCategory = category;
        this.expires = expires;
        this.title = title;
        this.description = description;
        this.vacancy = vacancy;
    }

    //constructor to display data on editpost.jsp file - user
    public Post(String title, String description, int vacancy) {
        this.title = title;
        this.description = description;
        this.vacancy = vacancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(int jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public int getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(int jobCategory) {
        this.jobCategory = jobCategory;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(int jobLocation) {
        this.jobLocation = jobLocation;
    }

    public LocalDate getPosted() {
        return posted;
    }

    public void setPosted(LocalDate posted) {
        this.posted = posted;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public void setExpires(LocalDate expires) {
        this.expires = expires;
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

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public InputStream getLogo() {
        return logo;
    }

    public void setImgPath(InputStream logo) {
        this.logo = logo;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public String getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", jobTypeID=" + jobTypeID +
                ", jobCategory=" + jobCategory +
                ", companyID=" + companyID +
                ", jobLocation=" + jobLocation +
                ", vacancy=" + vacancy +
                ", posted=" + posted +
                ", expires=" + expires +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", companyDesc='" + companyDesc + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", companyWeb='" + companyWeb + '\'' +
                ", salary='" + salary + '\'' +
                ", jobType='" + jobType + '\'' +
                ", category='" + category + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", imgPath='" + logo + '\'' +
                '}';
    }

    //    public static void main(String[] args) {
//        JobPostDAO dao = new JobPostDAO();
//        List<Post> posts = dao.getMyPosts(3);
//        for (Post p : posts) {
//            System.out.println(p.toString());
//        }
//    }
}
