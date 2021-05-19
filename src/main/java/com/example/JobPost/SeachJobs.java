package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "Searchjobs", value = "/seachjobs")
public class SeachJobs extends HttpServlet {

    private JobPostDAO dao = new JobPostDAO();
    private List<Post> posts;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String action = request.getParameter("type");

        switch (action) {
            case "user":
                searchUser(request, response);
                break;
            case "client":
                search(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;

        }

//        search(request, response);

    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String sortBy = request.getParameter("sortbydate");
        String userid = request.getParameter("userid");

        int id = 0;
        if(userid != null || !userid.isEmpty()) {
            id = toInt(userid);
        }

        System.out.println(id);
        if (sortBy.equals("date_posted")) {
            request.getRequestDispatcher("/WEB-INF/view/user/job_listing.jsp").forward(request, response);
            return;
        }

        if(sortBy.equals("expiration_date")) {
            posts = dao.displayJobPosts();
            Collections.sort(posts, new Comparator<Post>()
            {
                @Override
                public int compare(Post o1, Post o2) {
                    return o2.getExpires().compareTo(o1.getExpires());
                }
            });

            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/WEB-INF/view/user/job_listing.jsp?sortby="+sortBy).forward(request, response);
            return;
        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String sortBy = request.getParameter("sortbydate");

        if (sortBy.equals("date_posted")) {
            response.sendRedirect("job_listing.jsp");
            return;
        }

        if(sortBy.equals("expiration_date")) {
            posts = dao.displayJobPosts();
            Collections.sort(posts, new Comparator<Post>()
            {
                @Override
                public int compare(Post o1, Post o2) {
                    return o2.getExpires().compareTo(o1.getExpires());
                }
            });

            request.setAttribute("posts", posts);
            response.sendRedirect("job_listing.jsp?sortby="+sortBy);
            return;
        }
    }

    public int toInt(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }
}
