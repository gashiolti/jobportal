package com.example.JobPost;

import java.io.InputStream;
import java.sql.Blob;

public class CompanyImage {

    private int id, companyID, postID;
    private InputStream logo;

    public CompanyImage(int id, int companyID, InputStream logo, int postID) {
        this.id = id;
        this.companyID = companyID;
        this.logo = logo;
        this.postID = postID;
    }

    public CompanyImage(int companyID, InputStream logo, int postID) {
        this.companyID = companyID;
        this.logo = logo;
        this.postID = postID;
    }

    public CompanyImage(InputStream logo) {
        this.logo = logo;
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

    public InputStream getLogo() {
        return logo;
    }

    public void setCompanyImageUrl(InputStream logo) {
        this.logo = logo;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}
