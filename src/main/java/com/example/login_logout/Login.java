package com.example.login_logout;

import com.example.adminmanagment.dao.AdminDAO;
import com.example.adminmanagment.model.Admin;
import com.example.companymanagment.Company;
import com.example.companymanagment.CompanyDAO;
import com.example.crypto.Crypto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet(name = "Login", value = "/home")
public class Login extends HttpServlet {

    private AdminDAO dao = new AdminDAO();
    private CompanyDAO companyDAO = new CompanyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

            redirectToHomePage(request, response);
    }

    public void redirectToHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        String error = "";
        String p = "/";

        if(email != null && !email.isEmpty() && pass != null && !pass.isEmpty()) {

            if(isAdmin(email, pass, request, response)) {
                HttpSession userSession = request.getSession();
                userSession.setAttribute("ADMIN_USER", email);
                request.getRequestDispatcher("/WEB-INF/view/admin/index.jsp").forward(request, response);
                return;
            }
            else {
                List<Company> companies = companyDAO.getCompanies();
                String hashedPass = Crypto.encode(pass);

                for(Company c : companies) {
                    if(c.getEmail().equals(email) && c.getPass().equals(hashedPass)) {
                        HttpSession user = request.getSession();
                        user.setAttribute("email", c.getEmail());
                        HttpSession userid = request.getSession();
                        int id = 0;
                        try {
                            id = c.getId();
                        } catch (Exception e) { e.printStackTrace(); }

                        userid.setAttribute("userid", id);
                        request.getRequestDispatcher("/WEB-INF/view/user/home.jsp?userid=" + id).
                                forward(request, response);
                        return;
                    }
                }

                error = "Wrong login credentials, please try again!";
                response.sendRedirect("login.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
                return;
            }
        }
        else {
            error = "Please fill both textboxs!";
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

    }

    private boolean isAdmin(String email, String password, HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        List<Admin> adminList = dao.selectAdmins();
        String inputHashedPassword = Crypto.encode(password);
        boolean isAdmin = false;

        for (Admin a : adminList)
            if(email.equals(a.getEmail()) && inputHashedPassword.equals(a.getPassword()))
                if(a.getRole() == 1) {
                    request.getSession().setAttribute("name", a.getName());
                    isAdmin = true;
                } else {
                    isAdmin = false;
                }


        return isAdmin;
    }


}
