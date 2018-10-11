package com.backend.datastores.details;

public class LocalhostMYSQLConnectionDetails implements DataBaseConnectionDetails {

    public String getUrl() {
        return "jdbc:mysql://localhost:3306/java_auth_service";
    }

    public String getUser() {
        return "root";
    }

    public String getPassword() {
        return "";
    }
}
