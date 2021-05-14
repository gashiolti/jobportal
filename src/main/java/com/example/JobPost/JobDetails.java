package com.example.JobPost;

public class JobDetails {

    private int id, jobPostID;
    private String knowledgeSkillAbilities, educationExperience;

    public JobDetails(int id, int jobPostID, String knowledgeSkillAbilities, String educationExperience) {
        this.id = id;
        this.jobPostID = jobPostID;
        this.knowledgeSkillAbilities = knowledgeSkillAbilities;
        this.educationExperience = educationExperience;
    }

    public JobDetails(int jobPostID, String knowledgeSkillAbilities, String educationExperience) {
        this.jobPostID = jobPostID;
        this.knowledgeSkillAbilities = knowledgeSkillAbilities;
        this.educationExperience = educationExperience;
    }

    public JobDetails(String knowledgeSkillAbilities, String educationExperience) {
        this.knowledgeSkillAbilities = knowledgeSkillAbilities;
        this.educationExperience = educationExperience;
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

    public String getKnowledgeSkillAbilities() {
        return knowledgeSkillAbilities;
    }

    public void setKnowledgeSkillAbilities(String knowledgeSkillAbilities) {
        this.knowledgeSkillAbilities = knowledgeSkillAbilities;
    }

    public String getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }

    @Override
    public String toString() {
        return "JobRequirements{" +
                "id=" + id +
                ", jobPostID=" + jobPostID +
                ", knowledgeSkillAbilities='" + knowledgeSkillAbilities + '\'' +
                ", educationExperience='" + educationExperience + '\'' +
                '}';
    }
}
