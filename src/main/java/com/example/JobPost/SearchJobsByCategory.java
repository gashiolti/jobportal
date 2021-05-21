package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SearchJobsByCategory", value = "/search")
public class SearchJobsByCategory extends HttpServlet {

    private JobPostDAO dao = new JobPostDAO();
    private List<Post> posts;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String action = request.getParameter("usertype");

        switch (action) {
            case "client":
                search(request, response);
                break;
            case "user":
                searchUser(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String category = request.getParameter("category");
        int categoryID = 0;
        if(category != null) {
            categoryID = toInt(category);
        }

        if(categoryID != 0) {
            posts = dao.displayJobPosts();
            int finalCategoryID1 = categoryID;
            System.out.println("finalCategoryID1: "+categoryID);
            List<Post> set = posts.stream().filter(post -> post.getJobCategory() == finalCategoryID1).collect(Collectors.toList());
            request.getSession().setAttribute("category", set);
            int total = set.size();
            request.getSession().setAttribute("size", total);
            response.sendRedirect("job_listing.jsp?category="+finalCategoryID1+"&usertype=client");
            return;
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String category = request.getParameter("category");
        int categoryID = 0;
        if(category != null) {
            categoryID = toInt(category);
        }

        if(categoryID != 0) {
            posts = dao.displayJobPosts();
            int finalCategoryID1 = categoryID;
            System.out.println("finalCategoryID1: "+categoryID);
            List<Post> set = posts.stream().filter(post -> post.getJobCategory() == finalCategoryID1).collect(Collectors.toList());
            request.getSession().setAttribute("set", set);
            int total = set.size();
            request.getSession().setAttribute("size", total);
            request.getRequestDispatcher("/WEB-INF/view/user/job_listing.jsp?category="+finalCategoryID1+"&usertype" +
                    "=user").forward(request, response);
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
