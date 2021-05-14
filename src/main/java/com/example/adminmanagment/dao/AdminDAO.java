package com.example.adminmanagment.dao;


import com.example.adminmanagment.model.Admin;
import com.example.companymanagment.Company;
import com.example.crypto.Crypto;
import com.example.login_logout.Login;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//this DAO class provides CRUD database operations for the table admin_user in the database
public class AdminDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/jobportal";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    private static final String insertAdmin = "INSERT INTO admin_user (admin_name, admin_address, admin_email, pass, " +
            "role_id) VALUES (?, ?, ?, ?, ?);";
    private static final String updateAdmin = "UPDATE admin_user SET admin_name = ?, admin_address = ?, admin_email =" +
            " ?, pass = ? WHERE id = ?";
    private static final String selectAllAdmins = "SELECT * FROM admin_user";
    private static final String selectAdminByID = "SELECT * FROM admin_user WHERE id = ?";
    private static final String deleteAdmin = "DELETE FROM admin_user WHERE id = ?";
    private static final String selectCompanies = "SELECT * FROM company";

    public AdminDAO() {
    }

    //METHOD TO CONNECT WITH DATABASE
    protected Connection getConn() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    //CREATE OR INSERT ADMIN
    public void insertAdmin(Admin admin) throws SQLException {

        if(insertAdmin == null) {
            throw new IllegalArgumentException("something not ok with the query!");
        }

        try(Connection connection = getConn();
            PreparedStatement stmt = connection.prepareStatement(insertAdmin)) {

            //set the values for sql
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getAddress());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setInt(5, 1);

            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //UPDATE ADMIN
    public boolean updateAdmin(Admin admin) throws SQLException {
        boolean rowUpdated;

        if(updateAdmin == null) {
            throw new IllegalArgumentException("something not ok with the query!");
        }

        try(Connection connection = getConn();
            PreparedStatement stmt = connection.prepareStatement(updateAdmin))
        {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getAddress());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setInt(5, admin.getId());

            //basically 0 means failed and 1 means successed
            rowUpdated = stmt.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    //SELECT ADMIN
    public Admin selectAdmin(int id) {
        Admin admin = null;

        //step 1: establish the connection
        try(Connection connection = getConn();
            //step 2: create the statement using conn object
            PreparedStatement stmt = connection.prepareStatement(selectAdminByID))
        {
            stmt.setInt(1, id);
            //System.out.println(stmt); //for debugging purposes

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String name = resultSet.getString("admin_name");
                String address = resultSet.getString("admin_address");
                String email = resultSet.getString("admin_email");
                String password = resultSet.getString("pass");

                int roleType = 1; //administrator
                admin = new Admin(id, name, address, email, password, roleType);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(admin, "admin shouldnt be null brudda");
        return admin;
    }

    //SELECT ALL ADMINS
    public List<Admin> selectAdmins() {
        List<Admin> admins = new ArrayList<Admin>();

        try(Connection conn = getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(selectAllAdmins))
        {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("admin_name");
                String address = rs.getString("admin_address");
                String email = rs.getString("admin_email");
                String password = rs.getString("pass");
                int roleType = rs.getInt("role_id");

//                System.out.println(name + ", " + address + ", " + email + ", " + password);
                admins.add(new Admin(id, name, address, email, password, roleType));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }



    //DELETE ADMIN
    public boolean deleteAdmin(int id) throws SQLException {
        boolean rowDeleted;

        try(Connection connection = getConn();
            PreparedStatement stmt = connection.prepareStatement(deleteAdmin))
        {
            stmt.setInt(1, id);
            rowDeleted = stmt.executeUpdate() > 0;
        }

        return rowDeleted;
    }

    public Admin getAdminInfo(int id) {
        Admin admin = null;
        try (Connection conn = getConn(); PreparedStatement statement = conn.prepareStatement(selectAdminByID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("company_name");
                String email = resultSet.getString("company_email");

                admin = new Admin(name, email);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    public List<Company> getCompanyDetails() {
        List<Company> companies = new ArrayList<Company>();

        try(Connection conn = getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(selectCompanies))
        {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("company_name");
                String address = rs.getString("company_address");
                String email = rs.getString("company_email");
                String password = rs.getString("pass");
                String website = rs.getString("company_website");

//                System.out.println(name + ", " + address + ", " + email + ", " + password);
                companies.add(new Company(id, name, address, email, password, website));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

//    public static int isNumber(String id) {
//        int userid = 0;
//        try {
//            userid = Integer.parseInt(id);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//        return userid;
//    }


//    public static void main(String[] args) {
//        AdminDAO dao = new AdminDAO();
//        System.out.println(dao.selectAdmin(1).toString());
//    }

}
