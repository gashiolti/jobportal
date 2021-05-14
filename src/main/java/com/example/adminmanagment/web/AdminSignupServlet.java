package com.example.adminmanagment.web;

import com.example.adminmanagment.dao.AdminDAO;
import com.example.adminmanagment.model.Admin;
import com.example.crypto.Crypto;

import javax.jms.Session;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/register")
public class AdminSignupServlet extends HttpServlet {

    private final AdminDAO adminDAO;

    public AdminSignupServlet() {
        this.adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

//        String action = request.getParameter("error");
//        switch (action) {
//            case:
//
//        }
        insertAdmin(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }

    private void insertAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirmpass");
        String address = request.getParameter("address");
//        String p = "pages/admin/";

//        String error = "";

        if(emptyInputSignUp(name, address, email, pass, confirmPass) != false) {
            String error = "Inputs must be filled!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                .forward(request, response);
//            response.sendRedirect(p + "register.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
//            response.sendRedirect("admin/register.jsp");
            return;
        }

        if(inputNameLength(name) != false) {
            String error = "Name should contain atlease 6 characters!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(invalidEmail(email) != false) {
            String error = "Invalid email!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(inputPassLength(pass) != false) {
            String error = "Password should contain atlease 8 characters!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(passwordMatch(pass, confirmPass) != false) {
            String error = "Passwords do not match!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(inputAddressLength(pass) != false) {
            String error = "Adress should contain atlease 5 characters!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error=" + URLEncoder.encode(error, "UTF-8"))
                    .forward(request, response);
            return;
        }

        if(emailExists(email) != false) {
            String error = "Email address already exists, please choose another one!";
            request.getSession().setAttribute("errorDisplay", error);
            request.getRequestDispatcher("/WEB-INF/view/admin/register.jsp?error" + URLEncoder.encode(error,
                    "UTF-8")).forward(request, response);
            return;
        }

        String encryptedPass = Crypto.encode(pass);
        Admin admin = new Admin(name, address, email, encryptedPass, 1);
        String success = "New admin has been created successfully!";

        try {
            adminDAO.insertAdmin(admin);
            int userID = admin.getId();
            request.getSession().setAttribute("name", admin.getName());
            request.getSession().setAttribute("ADMIN_USER", admin.getEmail());
//            request.getSession().setAttribute("CURRENT_USER", userID);
            request.getSession().setAttribute("success", success);
            request.getSession().setMaxInactiveInterval(60);
//            response.sendRedirect("WEB-INF/view/admin/home.jsp");
//            request.getRequestDispatcher("/WEB-INF/view/admin/home.jsp").forward(request, response);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/table.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //FUNCTION TO VALIDATE ADMIN INPUTS FORM
    private boolean emptyInputSignUp(String name, String address, String email, String password, String confirmPass) {
        boolean result;
        result = name.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty();
        return result;
    }

    private boolean inputNameLength(String name) {
        boolean result;
        result = name.length() < 6;
        return result;
    }

    public boolean invalidEmail(String email) {
        boolean result;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        result = !matcher.matches();

        return result;
    }

    public boolean passwordMatch(String password, String confirmPass) {
        boolean result;
        result = !password.equals(confirmPass);
        return result;
    }

    public boolean inputPassLength(String pass) {
        boolean result;
        result = pass.length() < 8;

        return result;
    }

    public boolean inputAddressLength(String address) {
        boolean result;
        result = address.length() < 5;

        return result;
    }

    private boolean emailExists(String input) {
        boolean error = true;
        AdminDAO adminDAO = new AdminDAO();
        List<Admin> adminList = adminDAO.selectAdmins();

        for (Admin a : adminList) {
            if(!a.getEmail().equals(input))
                error = false;
            else
                error = true;
        }
        return error;
    }




}
