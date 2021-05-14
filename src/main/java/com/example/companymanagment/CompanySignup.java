package com.example.companymanagment;

import com.example.crypto.Crypto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CompanySignup", value = "/index")
public class CompanySignup extends HttpServlet {

    private CompanyDAO companyDAO = new CompanyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        signup(request, response);
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get parameters from html sign up form
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phonenr = request.getParameter("phone_nr");
        String webUrl = request.getParameter("weburl");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("re_password");
        String description = request.getParameter("description");
        String checkBox = request.getParameter("agree-term");

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || address.isEmpty() || webUrl.isEmpty())
        {
            String error = "All textboxs must be filled!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        if(name.length() < 2) {
            String error = "Name should be longer than 3 characters!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        if(invalidEmail(email)) {
            String error = "This email is invalid. Please type another email!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        if(!password.equals(confirmPass)) {
            String error = "The given passwords do not match!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        if(address.length() < 2) {
            String error = "Address should be longer than 3 characters!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        if(invalidWebURL(webUrl) == false) {
            String error = "This web url is invalid!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        if(checkBox == null) {
            String error = "You should accept our term and services, so tick the box!";
            request.getSession().setAttribute("errorDisplay", error);
            response.sendRedirect("pages/signup.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
            return;
        }

        int role = 2;
        String hashedPassword = Crypto.encode(password);
        Company company = new Company(name, address, email, phonenr, webUrl, description, hashedPassword, role);

        try {
            companyDAO.insertCompany(company);
            List<Company> companies = companyDAO.getCompanies();
            HttpSession idSession = request.getSession();
            HttpSession emailSession = request.getSession();

            for(Company c : companies)
                if(c.getEmail().equals(email) && c.getPass().equals(hashedPassword)) {
                    idSession.setAttribute("userid", c.getId());
                    emailSession.setAttribute("email", c.getEmail());
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/user/home.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return;

    }

    public boolean invalidEmail(String email) {
        boolean result;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        result = !matcher.matches();

        return result;
    }



    public boolean invalidWebURL(String webURL) {
        try {
            URL url = new URL(webURL);
            URLConnection connection = url.openConnection();
            connection.connect();
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
}
