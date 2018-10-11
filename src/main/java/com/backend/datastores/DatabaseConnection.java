package com.backend.datastores;

import com.backend.datastores.details.DataBaseConnectionDetails;

import java.sql.*;

public class DatabaseConnection {
    protected String url;
    protected String user;
    protected String password;
    public DatabaseConnection(DataBaseConnectionDetails details) {
        // write your code here
        this.url = details.getUrl();
        this.user = details.getUser();
        this.password = details.getPassword();
    }

    /**
     * Get a connection to database
     * @return Connection object
     */
    public Connection getConnection() {

        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException ex) {

            throw new RuntimeException("Error connecting to the database", ex);

        }

    }
}
