package com.example.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDAO {

    //heroku
    private final String jdbcURL = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_7c5078b35daf2f4";
    private final String username = "bf8a27c6dad1c6";
    private final String password = "f426501a";

    private final static String insertContact = "INSERT INTO contact (full_name, email, message_subject, message)\n" +
                                                " VALUES (?, ?, ?, ?);";




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

    public void addContact(Contact contact) {
        if(insertContact == null) {
            throw new IllegalArgumentException();
        }

        try (Connection connection = getConn(); PreparedStatement statement = connection.prepareStatement(insertContact))
        {
            statement.setString(1, contact.getFullName());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getMessageSubject());
            statement.setString(4, contact.getMessage());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
