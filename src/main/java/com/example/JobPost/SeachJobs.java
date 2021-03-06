package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        String category = request.getParameter("category");
        int categoryID = 0;
        if(category != null) {
            categoryID = toInt(category);
        }
        String type = request.getParameter("job_type");
        int typeID = 0;
        if(type != null) {
            typeID = toInt(type);
        }
        String location = request.getParameter("location");

        if(sortBy != null) {
            if (sortBy.equals("date_posted")) {
                request.getRequestDispatcher("/WEB-INF/view/user/job_listing.jsp").forward(request, response);
                return;
            }

            if (sortBy.equals("expiration_date")) {
                posts = dao.displayJobPosts();
                Collections.sort(posts, new Comparator<Post>() {
                    @Override
                    public int compare(Post o1, Post o2) {
                        return o2.getExpires().compareTo(o1.getExpires());
                    }
                });

                request.getSession().setAttribute("posts", posts);
                request.getRequestDispatcher("/WEB-INF/view/user/job_listing.jsp?sortby=" + sortBy).forward(request, response);
                return;
            }
        }

        if(categoryID != 0 || typeID != 0 || location != null) {
            posts = dao.displayJobPosts();
            int finalCategoryID = categoryID;
            int finalTypeID = typeID;
            List<Post> set = posts.stream().filter(post -> post.getJobCategory() == finalCategoryID && post.getJobTypeID()
                    == finalTypeID && post.getLocation().contains(location)).collect(Collectors.toList());
            request.getSession().setAttribute("set", set);
            int total = set.size();
            request.getSession().setAttribute("size", total);
            request.getRequestDispatcher("/WEB-INF/view/user/job_listing.jsp?categoryid="+categoryID+"&typeid="+typeID+
                    "&location=" +location).forward(request, response);
            return;
        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String sortBy = request.getParameter("sortbydate");
        String category = request.getParameter("category");
        int categoryID = 0;
        if(category != null) {
            categoryID = toInt(category);
        }
        String type = request.getParameter("job_type");
        int typeID = 0;
        if(type != null) {
            typeID = toInt(type);
        }
        String location = request.getParameter("location");

        if(sortBy != null) {
            if (sortBy.equals("date_posted")) {
                response.sendRedirect("job_listing.jsp?sortby="+sortBy);
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

                request.getSession().setAttribute("posts", posts);
                response.sendRedirect("job_listing.jsp?sortby="+sortBy);
                return;
            }
        }

        if(categoryID != 0 || typeID != 0 || location != null) {
            posts = dao.displayJobPosts();
            int finalCategoryID = categoryID;
            int finalTypeID = typeID;
            List<Post> set = posts.stream().filter(post -> post.getJobCategory() == finalCategoryID && post.getJobTypeID()
                    == finalTypeID && post.getLocation().contains(location)).collect(Collectors.toList());
            request.getSession().setAttribute("set", set);
            int total = set.size();
            request.getSession().setAttribute("size", total);
            response.sendRedirect("job_listing.jsp?categoryid="+categoryID+"&typeid="+typeID+"&location="
                    +location);
            return;
        }

        if(categoryID != 0) {
            posts = dao.displayJobPosts();
            int finalCategoryID1 = categoryID;
            System.out.println("finalCategoryID1: "+categoryID);
            List<Post> set = posts.stream().filter(post -> post.getJobCategory() == 1).collect(Collectors.toList());
            request.setAttribute("set", set);
            int total = set.size();
            request.setAttribute("size", total);
//            response.sendRedirect("job_listing.jsp?category="+finalCategoryID1+"&usertype=client");
            response.sendRedirect("login.jsp");
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
