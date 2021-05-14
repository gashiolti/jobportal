package com.example.redirect;

import com.example.JobPost.JobPostDAO;
import com.example.JobPost.Post;
import com.example.adminmanagment.dao.AdminDAO;
import com.example.companymanagment.Company;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dispatch")
public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String action = request.getParameter("page");

        switch (action) {
            case "table":
                redirectToTablePage(request, response);
                break;
            case "chart":
                redirectToChartPage(request, response);
                break;
            case "index":
                redirectToIndexPage(request, response);
                break;
            case "signup":
                redirectToSignUpPage(request, response);
                break;
            case "edituser":
                redirectToUpdateAdminPage(request, response);
                break;
            case "postjob":
                redirectToPostjobPage(request, response);
                break;
            case "profile":
                redirectToProfilePage(request, response);
                break;
            case "home":
                redirectToHomePage(request, response);
                break;
            case "myposts":
                redirectToMyPostsPage(request, response);
                break;
            case "editpost":
                redirectToEditPostPage(request, response);
                break;
            case "jobdetails":
                redirectToJobDetailsPage(request, response);
                break;
            case "postdetails":
                redirectToJobDetailsPage2(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    protected void redirectToTablePage(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        AdminDAO dao = new AdminDAO();
        List<Company> companyList = dao.getCompanyDetails();
        request.setAttribute("companies", companyList);

        JobPostDAO dao1 = new JobPostDAO();
        List<Post> postList = dao1.listJobPostsAdmin();
        request.setAttribute("posts", postList);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/table.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void redirectToChartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/chart.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirectToIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/home.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirectToSignUpPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/register.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirectToUpdateAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/editadmin.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirectToPostjobPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/postjob.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirectToProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/profile" +
                ".jsp?userid=" + userid);
        dispatcher.forward(request, response);
    }

    protected void redirectToHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/home.jsp");
        dispatcher.forward(request, response);
    }

    protected void redirectToMyPostsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/myposts" +
                ".jsp?userid=" + userid);
        dispatcher.forward(request, response);
    }

    protected void redirectToEditPostPage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/editpost" +
                ".jsp?userid=");
        dispatcher.forward(request, response);
    }

    //for user only
    protected void redirectToJobDetailsPage (HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int postid = Integer.parseInt(request.getParameter("postid"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/jobdetails.jsp" +
                "?postid="+postid);
        dispatcher.forward(request, response);
    }

    //for everyone who visits the website
    protected void redirectToJobDetailsPage2 (HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int postid = Integer.parseInt(request.getParameter("postid"));
        response.sendRedirect("pages/job_details.jsp?postid=" + postid);
    }

}
