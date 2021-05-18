package com.example.companymanagment;

import com.example.crypto.Crypto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CompanyUpdate", value = "/profileupdate")
public class CompanyUpdate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CompanyUpdate() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        updateProfile(request, response);
    }

    public void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String userid = request.getParameter("userid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNr = request.getParameter("phonenr");
        String website = request.getParameter("website");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        int id = idToInt(userid);
        String error = "";
        String message = "";

        if(id == 0 || name.isEmpty() || email.isEmpty() || phoneNr.isEmpty() || website.isEmpty() || address.isEmpty()
                || description.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            error = "Do not leave the textboxs empty!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/profile?error="+error).forward(request, response);
            return;
        }

        if((!pass.isEmpty() && confirmPass.isEmpty()) || (pass.isEmpty() && !confirmPass.isEmpty())) {
            error = "Please do not leave empty password or confirm password!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/profile.jsp?error="+error).forward(request, response);
            return;
        }

        if(!pass.equals(confirmPass)) {
            error = "Passwords do not match, try again!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/profile.jsp?error="+error).forward(request, response);
            return;
        }

        CompanyDAO dao = new CompanyDAO();
        String hashedpass = Crypto.encode(pass);
        try {
            dao.updateCompany(new Company(name, email, phoneNr, website, address, description, hashedpass, id));
            message = "Your profile has been successfully updated!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/view/user/profile.jsp?message="+message).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;


    }

    private int idToInt(String string) {
        int id = 0;
        try {
            id = Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return id;
    }
}
