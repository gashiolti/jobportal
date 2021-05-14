package com.example.adminmanagment.web;

import com.example.adminmanagment.dao.AdminDAO;
import com.example.adminmanagment.model.Admin;
import com.example.crypto.Crypto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/updateadmin")
public class ServletAdminUpdateServlet extends HttpServlet {

    private final AdminDAO adminDAO;

    public ServletAdminUpdateServlet() {
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
        updateAdmin(request, response);
    }

    public void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");


        if(name.isEmpty() || address.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            String error = "Inputs must be filled!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getSession().setMaxInactiveInterval(30);
            request.getRequestDispatcher("/WEB-INF/view/admin/editadmin.jsp?error=" + URLEncoder.encode(error, "UTF-8")
            + "&userid="+id).forward(request, response);
//            response.sendRedirect(p + "register.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
//            response.sendRedirect("admin/register.jsp");
            return;
        }

        if(name.length() < 5) {
            String error = "Name should have more than 6 characters";
            request.getSession().setAttribute("errorDisplay", error);
            request.getSession().setMaxInactiveInterval(30);
            request.getRequestDispatcher("/WEB-INF/view/admin/editadmin=.jsp?error=" + URLEncoder.encode(error, "UTF-8")
                    + "&userid="+id).forward(request, response);
            return;
        }

        if(pass.length() < 7) {
            String error = "Password should have more than 8 characters";
            request.getSession().setAttribute("errorDisplay", error);
            request.getSession().setMaxInactiveInterval(30);
            request.getRequestDispatcher("/WEB-INF/view/admin/editadmin=.jsp?error=" + URLEncoder.encode(error, "UTF-8")
                    + "&userid="+id).forward(request, response);
            return;
        }

        if(invalidEmail(email) != true) {
            String error = "Email is not valid, please choose another one!";
            request.getSession().setAttribute("errorDisplay", error + URLEncoder.encode(error, "UTF-8")
                    + "&userid="+id);
            request.getSession().setMaxInactiveInterval(30);
            request.getRequestDispatcher("/WEB-INF/view/admin/editadmin=.jsp?error=").forward(request, response);
            return;
        }

        Admin admin = new Admin(id, name, address, email, Crypto.encode(pass), 1);
        try {
            adminDAO.updateAdmin(admin);
            String success = "Admin updated successfully!";
            request.getSession().setAttribute("success", success);
            request.getSession().setMaxInactiveInterval(30);
            request.getRequestDispatcher("/WEB-INF/view/admin/table.jsp").forward(request, response);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean invalidEmail(String email) {
        boolean result;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        result = matcher.matches();

        return result;
    }


}


