package com.example.contact;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

@WebServlet(name = "ContactServlet", value = "/contact")
public class ContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String action = request.getParameter("usertype");

        switch (action) {
            case "user":
                insertContactUser(request, response);
                break;
            case "client":
                insertContact(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
//        insertContact(request, response);
    }

    private void insertContact(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String formMessage = request.getParameter("message");
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String error = "";
        String message = "";

        if(formMessage.isEmpty() || fullName.isEmpty() || email.isEmpty() || subject.isEmpty()) {
            error = "All textboxs should be filled!";
            request.getSession().setAttribute("error", error);
            response.sendRedirect("contact.jsp?error="+error);
            return;
        }

        ContactDAO dao = new ContactDAO();
        Contact contact = null;
        try {
            contact = new Contact(fullName, email, subject, formMessage);
            dao.addContact(contact);
            message = "Your contact form has been successfully submited!";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("contact.jsp?message="+message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    private void insertContactUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String formMessage = request.getParameter("message");
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String error = "";
        String message = "";

        if(formMessage.isEmpty() || fullName.isEmpty() || email.isEmpty() || subject.isEmpty()) {
            error = "All textboxs should be filled!";
            request.getSession().setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/view/user/contact.jsp?error="+error).forward(request, response);
            return;
        }

        ContactDAO dao = new ContactDAO();
        Contact contact = null;
        try {
            contact = new Contact(fullName, email, subject, formMessage);
            dao.addContact(contact);
            message = "Your contact form has been successfully submited!";
            request.getSession().setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/view/user/contact.jsp?message="+message).forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
