package com.example.companymanagment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    //mysql
//    private final String jdbcURL = "jdbc:mysql://localhost:3306/jobportal";
//    private final String username = "root";
//    private final String password = "";

    //heroku
    private final String jdbcURL = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_7c5078b35daf2f4";
    private final String username = "bf8a27c6dad1c6";
    private final String password = "f426501a";

    private static final String insertCompany = "INSERT INTO company(company_name, company_address, company_email, " +
            "phone_nr, company_website, description, pass, role_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectCompanies = "SELECT * FROM company";
    private static final String selectCompany = "SELECT * FROM company WHERE id = ?";
    private static final String updateCompany = "UPDATE company SET company_name = ?, company_address = ?, " +
            "company_email = ?, phone_nr = ?, company_website = ?, description = ?, pass = ? WHERE id = ?";
    private static final String deleteCompany = "DELETE FROM company WHERE id = ?";


    public CompanyDAO() {

    }

    private Connection getConn() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insertCompany(Company company) throws SQLException {
        if(insertCompany == null)
            throw new IllegalArgumentException("something is wrong with the query, take a look at it");

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(insertCompany, Statement.RETURN_GENERATED_KEYS)) {

            //set the values for the sql
            int i = 1;
            statement.setString(i++, company.getName());
            statement.setString(i++, company.getAddress());
            statement.setString(i++, company.getEmail());
            statement.setString(i++, company.getPhoneNr());
            statement.setString(i++, company.getWebUrl());
            statement.setString(i++, company.getDescription());
            statement.setString(i++, company.getPass());
            statement.setInt(i++, company.getRole()); //2 for user

            statement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

        }

    }

    public boolean updateCompany(Company company) throws SQLException {
        boolean rowUpdated;

        if(updateCompany == null)
            throw new IllegalArgumentException("something is wrong with the query, fix it");

        try (Connection connection = getConn();
            PreparedStatement statement = connection.prepareStatement(updateCompany))
        {
            int i=1;
            statement.setString(i++, company.getName());
            statement.setString(i++, company.getAddress());
            statement.setString(i++, company.getEmail());
            statement.setString(i++, company.getPhoneNr());
            statement.setString(i++, company.getWebUrl());
            statement.setString(i++, company.getDescription());
            statement.setString(i++, company.getPass());
            statement.setInt(i++, company.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    public boolean deleteCompany(int id) throws SQLException {
        boolean rowDeleted;

        try(Connection connection = getConn();
            PreparedStatement stmt = connection.prepareStatement(deleteCompany))
        {
            stmt.setInt(1, id);
            rowDeleted = stmt.executeUpdate() > 0;
        }

        return rowDeleted;
    }

    public Company getCompany(int id) {
        Company company = null;

        try (Connection connection = getConn();
            PreparedStatement statement = connection.prepareStatement(selectCompany))
        {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("company_name");
                String address = resultSet.getString("company_address");
                String email = resultSet.getString("company_email");
                String phoneNr = resultSet.getString("phone_nr");
                String website = resultSet.getString("company_website");
                String description = resultSet.getString("description");
                String pass = resultSet.getString("pass");
                int role = resultSet.getInt("role_id");

                company = new Company(name, address, email, phoneNr, website, description, pass, role);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<Company>();

        try (Connection connection = getConn();
            PreparedStatement statement = connection.prepareStatement(selectCompanies))
        {
            ResultSet set = statement.executeQuery();
            while(set.next())  {
                int id = set.getInt("id");
                String name = set.getString("company_name");
                String address = set.getString("company_address");
                String email = set.getString("company_email");
                String phoneNr = set.getString("phone_nr");
                String website = set.getString("company_website");
                String description = set.getString("description");
                String pass = set.getString("pass");
                int role = set.getInt("role_id");

                companies.add(new Company(id, name, address, email, phoneNr, website, description, pass, role));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

//    public static void main(String[] args) throws SQLException {
////
//        CompanyDAO c = new CompanyDAO();
//        int role = 2;
//        c.insertCompany(new Company("google", "usa", "google@gmail.com", "google.com", "google123", role));
//
//    }

}
