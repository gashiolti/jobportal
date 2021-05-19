package com.example.JobPost;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "imageServlet", value = "/imageServlet")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String postid = request.getParameter("postid");
        int id = toInt(postid);
        JobPostDAO dao = new JobPostDAO();
        CompanyImage logo = dao.getCompanyImage(id);

        ServletOutputStream outputStream = response.getOutputStream();
        try {
            response.setContentType("image/jpeg");
            final byte bytes[] = new byte[10485760];
            int read = 0;
            while((read = logo.getLogo().read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
                System.out.println(outputStream);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream != null)
                outputStream.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    private int toInt(String string) {
        int num = 0;
        try {
            num = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }
}
