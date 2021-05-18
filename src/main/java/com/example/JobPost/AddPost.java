package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "AddPost", value = "/postadded")
@MultipartConfig
public class AddPost extends HttpServlet {

    private JobPostDAO jobPostDAO = new JobPostDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
//        request.getRequestDispatcher("/WEB-INF/view/user/postadded.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            insertPost(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void insertPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("userid"));
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String jobType = request.getParameter("jobtype");
        String salary = request.getParameter("salary");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String vacancy = request.getParameter("vacancy");
        String description = request.getParameter("description");
        String knowledgeSkillsAbilities = request.getParameter("ksa");
        String educationExperience = request.getParameter("ex");
        String expires = request.getParameter("expires");
        Part logo = request.getPart("logo");
        InputStream inputStream = null;
        String error = "";

        String fileName = Paths.get(logo.getSubmittedFileName()).getFileName().toString().toLowerCase();

        if(title.isEmpty() || category.isEmpty()  || jobType.isEmpty() || salary.isEmpty() || city.isEmpty() ||
                country.isEmpty() || vacancy.isEmpty() || description.isEmpty() || knowledgeSkillsAbilities.isEmpty() ||
                educationExperience.isEmpty() || logo == null)
        {
            error = "Please fill all the textfields!";
            request.getSession().setAttribute("errorMessage", error);
            request.getRequestDispatcher("/WEB-INF/view/user/postjob.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                    forward(request, response);
            return;
        }
        else if (description.length() > 1000) {
            error = "Maximum character length for description is 1000!";
            request.getSession().setAttribute("errorMessage", error);
            request.getRequestDispatcher("/WEB-INF/view/user/postjob.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                    forward(request, response);
            return;
        }
        else if (knowledgeSkillsAbilities.length() > 1000) {
            error = "Maximum character length for knowledge textbox is 1000!";
            request.getSession().setAttribute("errorMessage", error);
            request.getRequestDispatcher("/WEB-INF/view/user/postjob.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                    forward(request, response);
            return;
        }
        else if (educationExperience.length() > 1000) {
            error = "Maximum character length for education textbox is 1000!";
            request.getSession().setAttribute("errorMessage", error);
            request.getRequestDispatcher("/WEB-INF/view/user/postjob.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                    forward(request, response);
            return;
        }
        else {
            if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                if(logo.getSize() < 10485760) {

                    if(logo != null)
                        inputStream = logo.getInputStream();

                    String path = "C:\\upload\\";
                    String uniqueId = UUID.randomUUID().toString();
//                    OutputStream out = null;
//                    InputStream filecontent = null;

                    int jobTypeToInt = toInt(jobType);
                    int categoryToInt = toInt(category);
                    LocalDate postExpire = LocalDate.parse(expires, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate currentDate = new java.util.Date().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate();

                    try {
//                        out = new FileOutputStream(new File(path + uniqueId + "." + fileName));
//                        filecontent = logo.getInputStream();
//                        int read = 0;
//////                        10485760
//                        long size = logo.getSize();
//                        final byte bytes[] = new byte[(int) size];
////
//                        while((read = filecontent.read(bytes)) != -1) {
//                            out.write(bytes, 0, read);
//                        }

                        JobLocation location1 = new JobLocation(city, country);
                        jobPostDAO.addLocation(location1);

                        Post post = new Post(jobTypeToInt, categoryToInt, id, JobPostDAO.jobLocationID, currentDate, postExpire
                                , title, description, toInt(vacancy));
                        jobPostDAO.addPost(post);

                        Salary salary1 = new Salary(JobPostDAO.postID, salary);
                        jobPostDAO.addSalary(salary1);

                        JobDetails details = new JobDetails(JobPostDAO.postID, knowledgeSkillsAbilities,
                                educationExperience);
                        jobPostDAO.addJobDetails(details);

                        CompanyImage image = new CompanyImage(id, inputStream, JobPostDAO.postID);
                        jobPostDAO.addCompanyImage(image);

                        request.getRequestDispatcher("/WEB-INF/view/user/postadded.jsp").forward(request,
                                response);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    finally {
                        if(inputStream != null)
                            inputStream.close();
                    }
                    return;
                }
                else {
                    error = "This image is larger than 10MB, please choose another one!";
                    request.getSession().setAttribute("errorMessage", error);
                    request.getRequestDispatcher("/WEB-INF/view/user/postjob.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                            forward(request, response);
                    return;
                }
            }
            else {
                error = "This file is not an image. Please choose another one!";
                request.getSession().setAttribute("errorMessage", error);
                request.getRequestDispatcher("/WEB-INF/view/user/postjob.jsp?error="+ URLEncoder.encode(error, "UTF-8")).
                        forward(request, response);
                return;
            }
        }


    }

    private static int toInt(String string) {
        int num = 0;
        try {
            num = Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }




}
