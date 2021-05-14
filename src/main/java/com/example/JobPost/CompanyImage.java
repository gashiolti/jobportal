package com.example.JobPost;

public class CompanyImage {

    private int id, companyID, postID;
    private String companyImageUrl;

    public CompanyImage(int id, int companyID, String companyImageUrl, int postID) {
        this.id = id;
        this.companyID = companyID;
        this.companyImageUrl = companyImageUrl;
        this.postID = postID;
    }

    public CompanyImage(int companyID, String companyImageUrl, int postID) {
        this.companyID = companyID;
        this.companyImageUrl = companyImageUrl;
        this.postID = postID;
    }

    public CompanyImage(String companyImageUrl) {
        this.companyImageUrl = companyImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyImageUrl() {
        return companyImageUrl;
    }

    public void setCompanyImageUrl(String companyImageUrl) {
        this.companyImageUrl = companyImageUrl;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}
