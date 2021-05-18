package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "editpost", value = "/editpost")
@MultipartConfig
public class EditPost extends HttpServlet {



    public EditPost() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            updatePost(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, SQLException {

        String id = request.getParameter("postid");
        int postid = toInt(id);
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        int categoryID = toInt(category);
        String type = request.getParameter("type");
        int typeID = toInt(type);
        String salary = request.getParameter("salary");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String vacancy = request.getParameter("vacancy");
        int vacancyToInt = toInt(vacancy);
        String description = request.getParameter("description");
        String knowledgeSkillsAbilities = request.getParameter("ksa");
        String educationExperience = request.getParameter("ee");
        String date = request.getParameter("expires");
        Part logo = request.getPart("file");
        InputStream inputStream = null;

        String fileName = Paths.get(logo.getSubmittedFileName()).getFileName().toString().toLowerCase();

        String error = "";
        String message = "";

        if(title == null || category == null || type == null || salary == null || city == null || country == null ||
            description == null || knowledgeSkillsAbilities == null || educationExperience == null || date == null
            || logo == null) {
            error = "No field should be empty!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/editpost.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(postid == 0 || title.isEmpty() || category.isEmpty() || type.isEmpty() || salary.isEmpty() || city.isEmpty()
                || country.isEmpty() || vacancyToInt == 0 || description.isEmpty() || knowledgeSkillsAbilities.isEmpty()
                || educationExperience.isEmpty() || date.isEmpty()) {
            error = "No field should be empty!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/editpost.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(description.length() > 1000 || knowledgeSkillsAbilities.length() > 1000 || educationExperience.length() > 1000) {
            error = "Maximum character length is 1000!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/editpost.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                    forward(request, response);
            return;
        }

        if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
            if (logo.getSize() < 10485760) {
                if(logo != null) {
                    System.out.println(logo.getName());
                    System.out.println(logo.getSize());
                    System.out.println(logo.getContentType());
                    inputStream = logo.getInputStream();
                }
                LocalDate expires = null;
                try {
                    expires = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    JobPostDAO dao = new JobPostDAO();
                    Post post = new Post(typeID, categoryID, expires, title, description, vacancyToInt);
                    dao.updatePost(post, postid);

                    JobLocation jobLocation = new JobLocation(city, country);
                    dao.updateJobLocation(jobLocation, postid);

                    JobDetails details = new JobDetails(knowledgeSkillsAbilities, educationExperience);
                    dao.updatePostDetails(details, postid);

                    Salary salary1 = new Salary(salary);
                    dao.updatePostSalary(salary1, postid);

                    CompanyImage image = new CompanyImage(inputStream);
                    dao.updateLogo(image, postid);




                    List<JobCategory> categories = dao.getCategories();
                    request.setAttribute("categories", categories);

                    List<JobType> types = dao.getJobTypes();
                    request.setAttribute("types", types);

                    Post post2 = dao.displayPost(postid);
                    request.setAttribute("post", post2);

                    Salary salary2 = dao.getSalary(postid);
                    request.setAttribute("salary", salary2);

                    JobLocation location = dao.getJobLocation(postid);
                    request.setAttribute("location", location);

                    JobDetails details2 = dao.getJobDetails(postid);
                    request.setAttribute("details", details2);

                    message = "Your post has been successfully been updated!";
                    request.setAttribute("message", message);
                    request.setAttribute("postid", postid);
                    request.getRequestDispatcher("/WEB-INF/view/user/editpost.jsp?postid="+postid+"&message="+
                            URLEncoder.encode(message, "UTF-8")).forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if(inputStream != null)
                        inputStream.close();
                }
                return;
            }
            else {
                error = "This image is larger than 10MB, please choose another one!";
                request.setAttribute("error", error);
                request.getRequestDispatcher("/WEB-INF/view/user/editpost.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                        forward(request, response);
                return;
            }
        }
        else {
            error = "This file is not an image. Please choose another one!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/editpost.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                    forward(request, response);
            return;
        }

    }

    private int toInt(String string) {
        int num = 0;
        try {
            num = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }

}
