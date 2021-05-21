package com.example.JobPost;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JobPostDAO {

    protected static int postID;
    protected static int jobLocationID;

    // mysql
//    private final String jdbcURL = "jdbc:mysql://localhost:3306/jobportal";
//    private final String username = "root";
//    private final String password = "";

    //heroku
    private final String jdbcURL = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_7c5078b35daf2f4";
    private final String username = "bf8a27c6dad1c6";
    private final String password = "f426501a";


    //google app engine
//    private static final String CREDENTIALS = "jdbc:mysql://google/jobportal?cloudSqlInstance=spatial-thinker-313915" +
//            ":europe-west6:jobportal&socketFactory=com.google.cloud.sql.myqsl" +
//            ".SocketFactory&useSSL=false&user=jobportal&password=";

    private final static String insertJobLocation = "INSERT INTO job_location (city, country) VALUES (?, ?)";
    private final static String insertPost = "INSERT INTO job_post (job_type_id, job_category, company_id, " +
            "job_location_id, date_posted, expires, job_title, job_description, vacancy) VALUES (?, ?, ?, ?, ?, ?, ?," +
            " ?, ?)";
    private final static String insertSalary = "INSERT INTO job_salary (job_post_id, salary) VALUES (?, ?)";
    private final static String insertDetails = "INSERT INTO job_post_requirements (job_post_id, " +
            "knowledge_skills_abilities, education_experience) VALUES (?, ?, ?)";
    private final static String selectPostById = "SELECT jp.*, c.*, jc.*, jl.*, jt.*, ci.logo\n" +
                                                "FROM job_post jp,\n" +
                                                "     company c,\n" +
                                                "     job_category jc,\n" +
                                                "     job_location jl,\n" +
                                                "     job_type jt,\n" +
                                                "     company_image ci\n" +
                                                "WHERE jp.company_id = c.id\n" +
                                                "  AND jp.job_category = jc.id\n" +
                                                "  AND jp.job_location_id = jl.id\n" +
                                                "  AND jp.job_type_id = jt.id\n" +
                                                "  AND ci.post_id = jp.id\n" +
                                                "  AND jp.id = ?;\n;";
    private final static String selectPostByUserID = "SELECT jp.*, c.*, jc.*, jl.*, jt.*\n" +
                                            "FROM job_post jp,\n" +
                                            "     company c,\n" +
                                            "     job_category jc,\n" +
                                            "     job_location jl,\n" +
                                            "     job_type jt\n" +
                                            "WHERE jp.company_id = c.id\n" +
                                            "  AND jp.job_category = jc.id\n" +
                                            "  AND jp.job_location_id = jl.id\n" +
                                            "  AND jp.job_type_id = jt.id\n" +
                                            "  AND c.id = ?;";
    private final static String insertImage = "INSERT INTO company_image (company_id, logo, post_id) VALUES " +
            "(?, ?, ?)";
    private final static String selectSalary = "SELECT * FROM job_salary WHERE job_post_id = ?";
    private final static String selectJobPostRequirements = "SELECT * FROM job_post_requirements WHERE job_post_id = ?";
    private final static String selectImage = "SELECT * FROM company_image WHERE post_id = ?";
    private final static String jobListPosts = "SELECT jp.*, c.*, jl.*, jt.*, ci.logo, js.*\n" +
                                                "FROM job_post jp,\n" +
                                                "     company c,\n" +
                                                "     job_location jl,\n" +
                                                "     job_type jt,\n" +
                                                "     company_image ci,\n" +
                                                "     job_salary js\n" +
                                                "WHERE jp.company_id = c.id\n" +
                                                "  AND jp.job_location_id = jl.id\n" +
                                                "  AND jp.job_type_id = jt.id\n" +
                                                "  AND ci.post_id = jp.id\n" +
                                                "  AND jp.id = js.job_post_id " +
                                                "ORDER BY jp.date_posted DESC;";
    private final static String findJobPostByTitle = "SELECT jp.*, c.*, jl.*, jt.*, ci.logo, js.*\n" +
                                                    "FROM job_post jp,\n" +
                                                    "     company c,\n" +
                                                    "     job_location jl,\n" +
                                                    "     job_type jt,\n" +
                                                    "     company_image ci,\n" +
                                                    "     job_salary js\n" +
                                                    "WHERE jp.company_id = c.id\n" +
                                                    "  AND jp.job_location_id = jl.id\n" +
                                                    "  AND jp.job_type_id = jt.id\n" +
                                                    "  AND ci.post_id = jp.id\n" +
                                                    "  AND jp.id = js.job_post_id\n" +
                                                    "  AND jp.job_title LIKE ?";
    private final static String findJobByTitleAndLocation = "SELECT jp.*, c.*, jl.*, jt.*, ci.logo, js.*\n" +
                                                            "FROM job_post jp,\n" +
                                                            "     company c,\n" +
                                                            "     job_location jl,\n" +
                                                            "     job_type jt,\n" +
                                                            "     company_image ci,\n" +
                                                            "     job_salary js\n" +
                                                            "WHERE jp.company_id = c.id\n" +
                                                            "  AND jp.job_location_id = jl.id\n" +
                                                            "  AND jp.job_type_id = jt.id\n" +
                                                            "  AND ci.post_id = jp.id\n" +
                                                            "  AND jp.id = js.job_post_id\n" +
                                                            "  AND jp.job_title LIKE ?\n" +
                                                            "  AND jl.country = ?";
    private final static String updatePost = "UPDATE job_post jp\n" +
                                            "SET jp.job_type_id                 = ?,\n" +
                                            "    jp.job_category                = ?,\n" +
                                            "    jp.expires                     = ?,\n" +
                                            "    jp.job_title                   = ?,\n" +
                                            "    jp.job_description             = ?,\n" +
                                            "    jp.vacancy                     = ?\n" +
                                            "WHERE jp.id = ?;";
    private final static String updateJobLocation = "UPDATE job_location jl, job_post jp\n" +
                                                    "SET jl.city    = ?,\n" +
                                                    "    jl.country = ?\n" +
                                                    "WHERE jp.job_location_id = jl.id\n" +
                                                    "  AND jp.id = ?;";
    private final static String updateJobPostRequirements = "UPDATE job_post_requirements\n" +
                                                    "SET knowledge_skills_abilities = ?,\n" +
                                                    "    education_experience       = ?\n" +
                                                    "WHERE job_post_id = ?;";
    private final static String updateSalary = "UPDATE job_salary\n" +
                                                "SET salary = ?\n" +
                                                "WHERE job_post_id = ?;";
    private final static String updatePostCompanyLogo = "UPDATE company_image\n" +
                                                        "SET logo = ?\n" +
                                                        "WHERE post_id = ?;";
    private final static String getPosts = "SELECT * FROM job_post";

    //select queries to display data on updatepost page - user
    private final static String selectJobCategories = "SELECT * FROM job_category";
    private final static String selectJobTypes = "SELECT * FROM job_type";
    private final static String selectJobLocation = "SELECT jl.* FROM job_location jl, job_post jp " +
                                                    "WHERE jp.job_location_id = jl.id AND jp.id = ?";
    private final static String selectPost = "SELECT * FROM job_post WHERE id = ?";
    private final static String getTotalPosts = "SELECT COUNT(*) FROM job_post";



    private Connection getConn() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void addLocation(JobLocation location) {
        if(insertJobLocation == null)
            throw new IllegalArgumentException();

        try (Connection connection = getConn(); PreparedStatement statement =
                connection.prepareStatement(insertJobLocation, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setString(i++, location.getCity());
            statement.setString(i++, location.getCountry());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
                jobLocationID = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPost(Post post) {
        if(insertPost == null)
            throw new IllegalArgumentException();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(insertPost,
                Statement.RETURN_GENERATED_KEYS)) {

            int i=1;
            statement.setInt(i++, post.getJobTypeID());
            statement.setInt(i++, post.getJobCategory());
            statement.setInt(i++, post.getCompanyID());
            statement.setInt(i++, post.getJobLocation());
            statement.setObject(i++, post.getPosted());
            statement.setObject(i++, post.getExpires());
            statement.setString(i++, post.getTitle());
            statement.setString(i++, post.getDescription());
            statement.setInt(i++, post.getVacancy());

            statement.executeUpdate();

            //get last inserted id on job_post table
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                postID = rs.getInt(1);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSalary(Salary salary) {
        if(insertSalary == null)
            throw new IllegalArgumentException();

        try (Connection connection = getConn(); PreparedStatement statement =
                connection.prepareStatement(insertSalary, Statement.RETURN_GENERATED_KEYS)) {

            int i = 1;
            statement.setInt(i++, postID);
            statement.setString(i++,salary.getSalary());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJobDetails(JobDetails details) {
        if(insertDetails == null)
            throw new IllegalArgumentException();

        try (Connection connection = getConn(); PreparedStatement statement =
                connection.prepareStatement(insertDetails, Statement.RETURN_GENERATED_KEYS)) {

            int i = 1;
            statement.setInt(i++, postID);
            statement.setString(i++, details.getKnowledgeSkillAbilities());
            statement.setString(i++, details.getEducationExperience());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCompanyImage(CompanyImage image) {
        if(insertImage == null)
            throw new IllegalArgumentException();

        try (Connection connection = getConn(); PreparedStatement statement =
                connection.prepareStatement(insertImage)) {

            int i = 1;
            statement.setInt(i++, image.getCompanyID());
            statement.setBlob(i++, image.getLogo());
            statement.setInt(i++, postID);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post getPost(int id) {
        Post post = null;

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectPostById))
        {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String companyName = set.getString("company_name");
                String title = set.getString("job_title");
                String jobType = set.getString("job_type");
                String jobCategory = set.getString("jc.job_category");
                String city = set.getString("city");
                String country = set.getString("country");
                String desc = set.getString("job_description");
                int vacancy = set.getInt("vacancy");
                LocalDate posted = set.getDate("date_posted").toLocalDate();
                LocalDate expires = set.getDate("expires").toLocalDate();
                InputStream logo = set.getBinaryStream("logo");
                String location = city + ", " + country;
                String companyDescription = set.getString("c.description");
                String companyEmail = set.getString("company_email");
                String web = set.getString("company_website");

                post = new Post(jobType, jobCategory, companyName, location, posted, expires, title, desc, vacancy,
                        logo, companyDescription, companyEmail, web);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    public List<Post> getMyPosts(int id) {
        List<Post> posts = new ArrayList<Post>();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectPostByUserID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("id");
                int companyID = rs.getInt("company_id");
                int jobTypeID = rs.getInt("job_type_id");
                int jobCategoryID = rs.getInt("job_category");
                int jobLocationID = rs.getInt("job_location_id");
                String companyName = rs.getString("company_name");
                String title = rs.getString("job_title");
                String jobType = rs.getString("job_type");
                String jobCategory = rs.getString("jc.job_category");
                String city = rs.getString("city");
                String country = rs.getString("country");
                int vacancy = rs.getInt("vacancy");
                LocalDate posted = rs.getDate("date_posted").toLocalDate();
                LocalDate expires = rs.getDate("expires").toLocalDate();
                String location = city + ", " + country;

                posts.add(new Post(postID, jobType, jobCategory, companyName, location, posted, expires, title,
                        vacancy));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    //display jobs list on job_listing.jsp file
    public List<Post> displayJobPosts() {
        List<Post> posts = new ArrayList<Post>();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(jobListPosts)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("id");
                int jobTypeID = rs.getInt("job_type_id");
                int jobCategoryID = rs.getInt("job_category");
                int locationID = rs.getInt("job_location_id");
                String companyName = rs.getString("company_name");
                String title = rs.getString("job_title");
                String jobType = rs.getString("job_type");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String salary = rs.getString("salary");
                LocalDate posted = rs.getDate("date_posted").toLocalDate();
                LocalDate expires = rs.getDate("expires").toLocalDate();
                String location = city + ", " + country;

                posts.add(new Post(postID, jobTypeID, jobCategoryID, locationID, jobType, companyName, location, posted,
                        expires, title, salary));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public Post displayPost(int postid) {
        Post post = null;

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectPost))
        {
            statement.setInt(1, postid);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String title = set.getString("job_title");
                String desc = set.getString("job_description");
                int vacancy = set.getInt("vacancy");

                post = new Post(title, desc, vacancy);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }


    public Salary getSalary(int postID) {
        Salary salary = null;

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectSalary))
        {
            statement.setInt(1, postID);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String salaryParam = set.getString("salary");

                salary = new Salary(salaryParam);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return salary;
    }

    public JobDetails getJobDetails(int postID) {
        JobDetails details = null;

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectJobPostRequirements))
        {
            statement.setInt(1, postID);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String skillsAndAbilities = set.getString("knowledge_skills_abilities");
                String educationAndExperience = set.getString("education_experience");

                details = new JobDetails(skillsAndAbilities, educationAndExperience);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }

    public CompanyImage getCompanyImage(int postID) {
        CompanyImage image = null;

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectImage))
        {
            statement.setInt(1, postID);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                InputStream logo = set.getBinaryStream("logo");
                image = new CompanyImage(logo);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return image;
    }

    //display job categories on post edit - user
    public List<JobCategory> getCategories() {
        List<JobCategory> categoryList = new ArrayList<JobCategory>();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectJobCategories)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("job_category");

                categoryList.add(new JobCategory(id, category));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    //display job types on post edit - user
    public List<JobType> getJobTypes() {
        List<JobType> jobTypes = new ArrayList<JobType>();

        try (Connection connection = getConn(); PreparedStatement statement =
                connection.prepareStatement(selectJobTypes)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("job_type");

                jobTypes.add(new JobType(id, type));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return jobTypes;
    }

    //display job types on post edit - user
    public JobLocation getJobLocation(int postid) {
        JobLocation location = null;

        try (Connection connection = getConn(); PreparedStatement statement =
                connection.prepareStatement(selectJobLocation)) {

            statement.setInt(1, postid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String city = rs.getString("city");
                String country = rs.getString("country");

                location = new JobLocation(city, country);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }


    //display jobs list from search bar in index.jsp file by title only
    public List<Post> searchJobsByTitle(String title) {
        List<Post> posts = new ArrayList<Post>();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(findJobPostByTitle)) {

            statement.setString(1, "%" + title + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("id");
                int jobTypeID = rs.getInt("job_type_id");
                int jobCategoryID = rs.getInt("job_category");
                int locationID = rs.getInt("job_location_id");
                String companyName = rs.getString("company_name");
                String jobTitle = rs.getString("job_title");
                String jobType = rs.getString("job_type");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String salary = rs.getString("salary");
                LocalDate posted = rs.getDate("date_posted").toLocalDate();
                LocalDate expires = rs.getDate("expires").toLocalDate();
                String location = city + ", " + country;

                posts.add(new Post(postID, jobTypeID, jobCategoryID, locationID, jobType, companyName, location, posted,
                        expires, jobTitle, salary));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    //display jobs list from search bar in index.jsp file by title and location
    public List<Post> searchJobsByTitleAndLocation(String title, String location) {
        List<Post> posts = new ArrayList<Post>();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(findJobPostByTitle)) {

            statement.setString(1, "%" + title + "%");
            statement.setString(2, location);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("id");
                int jobTypeID = rs.getInt("job_type_id");
                int jobCategoryID = rs.getInt("job_category");
                int locationID = rs.getInt("job_location_id");
                String companyName = rs.getString("company_name");
                String jobTitle = rs.getString("job_title");
                String jobType = rs.getString("job_type");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String salary = rs.getString("salary");
                LocalDate posted = rs.getDate("date_posted").toLocalDate();
                LocalDate expires = rs.getDate("expires").toLocalDate();
                String jobLocation = city + ", " + country;

                posts.add(new Post(postID, jobTypeID, jobCategoryID, locationID, jobType, companyName, jobLocation,
                        posted, expires, jobTitle, salary));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // display posts on admin panel
    public List<Post> listJobPostsAdmin() {
        List<Post> posts = new ArrayList<Post>();

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(jobListPosts)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("id");
                String companyName = rs.getString("company_name");
                String title = rs.getString("job_title");
                String jobType = rs.getString("job_type");
                String jobCategory = rs.getString("job_category");
                String city = rs.getString("city");
                String country = rs.getString("country");
                int vacancy = rs.getInt("vacancy");
                LocalDate posted = rs.getDate("date_posted").toLocalDate();
                LocalDate expires = rs.getDate("expires").toLocalDate();
                String location = city + ", " + country;

                posts.add(new Post(postID, jobType, jobCategory, companyName, location, posted, expires, title,
                        vacancy));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    //update post from user
    public boolean updatePost(Post post, int id) throws SQLException {
        boolean rowUpdated;

        if(updatePost == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(updatePost))
        {
            int i=1;
            statement.setInt(i++, post.getJobTypeID());
            statement.setInt(i++, post.getJobCategory());
            statement.setObject(i++, post.getExpires());
            statement.setString(i++, post.getTitle());
            statement.setString(i++, post.getDescription());
            statement.setInt(i++, post.getVacancy());
            statement.setInt(i++, id);

            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    //update post from user
    public boolean updateJobLocation(JobLocation jobLocation, int postid) throws SQLException {
        boolean rowUpdated;

        if(updatePost == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(updateJobLocation))
        {
            int i=1;
            statement.setString(i++, jobLocation.getCity());
            statement.setString(i++, jobLocation.getCountry());
            statement.setInt(i++, postid);

            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    //update post from user
    public boolean updatePostDetails(JobDetails jobDetails, int postid) throws SQLException {
        boolean rowUpdated;

        if(updatePost == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(updateJobPostRequirements))
        {
            int i=1;
            statement.setString(i++, jobDetails.getKnowledgeSkillAbilities());
            statement.setString(i++, jobDetails.getEducationExperience());
            statement.setInt(i++, postid);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    //update post from user
    public boolean updatePostSalary(Salary salary, int postid) throws SQLException {
        boolean rowUpdated;

        if(updatePost == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(updateSalary))
        {
            int i=1;
            statement.setString(i++, salary.getSalary());
            statement.setInt(i++, postid);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    //update company logo from user
    public boolean updateLogo(CompanyImage image, int postid) throws SQLException {
        boolean rowUpdated;

        if(updatePost == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(updatePostCompanyLogo))
        {
            int i=1;
            statement.setBlob(i++, image.getLogo());
            statement.setInt(i++, postid);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public int totalPosts() throws SQLException {
        int total = 0;
        if(getTotalPosts == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(getTotalPosts))
        {
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                total = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

//    public static void main(String[] args) throws SQLException {
//
//        JobPostDAO dao = new JobPostDAO();
//        List<Post> posts = dao.displayJobPosts();
//        List<Post> set = posts.stream().filter(post -> post.getJobCategory() == 1).collect(Collectors.toList());
//        set.forEach(System.out::println);
//    }

}
