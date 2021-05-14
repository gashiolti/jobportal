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

        String jobTitle = request.getParameter("title");
        String jobLocation = request.getParameter("select");
        String type = request.getParameter("type");

        if(!jobTitle.isEmpty() && jobTitle != null) {
                List<Post> posts = dao.searchJobsByTitle(jobTitle);
                if(type == "user") {
                    request.setAttribute("posts", posts);
                    request.setAttribute("jobtitle", jobTitle);
                    request.getRequestDispatcher("/WEB-INF/view/user/searchjob.jsp?title=" + jobTitle).
                            forward(request, response);
                    return;
                }
                else {
                    request.setAttribute("posts", posts);
                    request.setAttribute("jobtitle", jobTitle);
                    request.getRequestDispatcher("/WEB-INF/view/client/searchjob.jsp?title=" + jobTitle).
                            forward(request, response);
                    return;
                }
        }
        else if (!jobTitle.isEmpty() && jobTitle != null && !jobLocation.isEmpty() && jobLocation != null) {
            List<Post> posts = dao.searchJobsByTitleAndLocation(jobTitle, jobLocation);
            if(type == "user") {
                request.setAttribute("posts", posts);
                request.setAttribute("jobtitle", jobTitle);
                request.getRequestDispatcher("/WEB-INF/view/user/searchjob.jsp?title="+jobTitle+"&location="+jobLocation).
                        forward(request, response);
                return;
            }
            else {
                request.setAttribute("posts", posts);
                request.setAttribute("jobtitle", jobTitle);
                request.getRequestDispatcher("/WEB-INF/view/client/searchjob.jsp?title="+jobTitle+"&location="+jobLocation).
                        forward(request, response);
                return;
            }
        }
        else {
            if(type == "user") {
                request.getRequestDispatcher("/WEB-INF/view/user/home.jsp").
                        forward(request, response);
                return;
            }
            response.sendRedirect(request.getContextPath()+"/pages/index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
