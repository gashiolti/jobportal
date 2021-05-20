package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchpost", value = "/searchpost")
public class Searchpost extends HttpServlet {

    private JobPostDAO dao = new JobPostDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String type = request.getParameter("usertype");
        switch (type) {
            case "client":
                searchClient(request, response);
                break;
            case "user":
                searchUser(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void searchClient(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String jobTitle = request.getParameter("title");
        String jobLocation = request.getParameter("select");
        int totalPosts = 0;

        if(!jobTitle.isEmpty() && jobTitle != null) {
            List<Post> posts = dao.searchJobsByTitle(jobTitle);
            totalPosts = posts.size();
            request.getSession().setAttribute("total", totalPosts);
            request.getSession().setAttribute("posts", posts);
            request.getSession().setAttribute("jobtitle", jobTitle);
            response.sendRedirect("searchjob.jsp?title=" + jobTitle);
            return;
        }
        else if (!jobTitle.isEmpty() && jobTitle != null && !jobLocation.isEmpty() && jobLocation != null) {
            List<Post> posts = dao.searchJobsByTitleAndLocation(jobTitle, jobLocation);
            totalPosts = posts.size();
            request.getSession().setAttribute("total", totalPosts);
            request.getSession().setAttribute("posts", posts);
            request.getSession().setAttribute("jobtitle", jobTitle);
            response.sendRedirect("searchjob.jsp?title="+jobTitle+"&location="+jobLocation);
            return;
        }
        else {
            response.sendRedirect("index.jsp");
            return;
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String jobTitle = request.getParameter("title");
        String jobLocation = request.getParameter("select");
        int totalPosts = 0;

        if(!jobTitle.isEmpty() && jobTitle != null) {
            List<Post> posts = dao.searchJobsByTitle(jobTitle);
            totalPosts = posts.size();
            request.getSession().setAttribute("total", totalPosts);
            request.getSession().setAttribute("posts", posts);
            request.setAttribute("jobtitle", jobTitle);
            request.getRequestDispatcher("/WEB-INF/view/user/searchjob.jsp?title=" + jobTitle).
                    forward(request, response);
            return;
        }
        else if (!jobTitle.isEmpty() && jobTitle != null && !jobLocation.isEmpty() && jobLocation != null) {
            List<Post> posts = dao.searchJobsByTitleAndLocation(jobTitle, jobLocation);
            totalPosts = posts.size();
            request.getSession().setAttribute("total", totalPosts);
            request.getSession().setAttribute("posts", posts);
            request.setAttribute("jobtitle", jobTitle);
            request.getRequestDispatcher("/WEB-INF/view/user/searchjob.jsp?title="+jobTitle+"&location="+jobLocation).
                    forward(request, response);
            return;
        }
        else {
            request.getRequestDispatcher("/WEB-INF/view/user/home.jsp").forward(request, response);
            return;
        }
    }


}
