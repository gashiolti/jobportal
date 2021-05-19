package com.example.JobPortal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO {

//    private final String jdbcURL = "jdbc:mysql://localhost:3306/jobportal";
//    private final String jdbcUsername = "root";
//    private final String jdbcPassword = "";

    //heroku
    private final String jdbcURL = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_7c5078b35daf2f4";
    private final String username = "bf8a27c6dad1c6";
    private final String password = "f426501a";

    private final String selectCategories = "SELECT * FROM job_category";
    private final String selectJobTypes = "SELECT * FROM job_type";

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


    public Map<String, String> getCategories() {
        Map<String, String> categories = new HashMap<String, String>();

        try (Connection connection = getConn();
             PreparedStatement statement = connection.prepareStatement(selectCategories))
        {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("job_category");
                String idToString = String.valueOf(id);

                categories.put(idToString, category);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public Map<String, String> getJobTypes() {
        Map<String, String> jobTypes = new HashMap<String, String>();

        try(Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(selectJobTypes))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String jobType = resultSet.getString("job_type");
                String idToString = String.valueOf(id);

                jobTypes.put(idToString, jobType);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return jobTypes;
    }

    private static boolean isempty(String string) {
        if(string.isEmpty())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {

        String empty = "";
        if(isempty(empty))
            System.out.println("false");
        else
            System.out.println("true");
    }
}
