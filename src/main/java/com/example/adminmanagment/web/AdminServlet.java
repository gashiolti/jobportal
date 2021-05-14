package com.example.adminmanagment.web;

import com.example.adminmanagment.dao.AdminDAO;
import com.example.adminmanagment.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private static final long serialVersionAID = 1L;
    private final AdminDAO adminDAO;

    public AdminServlet() {
        this.adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

//        response.setContentType("text/html;charset=UTF-8");
//        listAdmins(request, response);

//        insertAdmin(request, response);
        String action = request.getParameter("page");

        switch (action) {
            case "deleteuser":
                deleteAdmin(request, response);
                break;

        }
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String userID = request.getParameter("userid");
        System.out.println("user id: " + userID);

        try {
            String success = "Admin deleted successfully!";
            int id = Integer.parseInt(userID);
            System.out.println("id: " + id);
            adminDAO.deleteAdmin(id);
            request.getSession().setAttribute("success", success);
            request.getRequestDispatcher("/WEB-INF/view/admin/table.jsp?message="+ URLEncoder.encode(success,
                    "UTF-8")).forward(request, response);
            return;
        }
        catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/home.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter(""));

        try {
            adminDAO.deleteAdmin(id);
            response.sendRedirect("home.jsp");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



//    public void listAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        response.setContentType("text/html;charset=UTF-8");
//
//            List<Admin> admins = adminDAO.selectAdmins();
//            request.setAttribute("data", admins);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/table.jsp");
//            requestDispatcher.forward(request, response);
////            response.sendRedirect("admin/table.jsp");
//
//    }



}
