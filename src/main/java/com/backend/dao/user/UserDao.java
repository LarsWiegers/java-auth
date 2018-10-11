package com.backend.dao.user;

import com.backend.models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    User getUser(int id) throws SQLException, NoUserFoundException;
    ArrayList<User> getAllUsers() throws SQLException;
    boolean insertUser(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(User user) throws SQLException;
    User getUserByEmail(String email) throws SQLException, NoUserFoundException;
}
