package com.backend.dao.user;

import com.backend.datastores.DatabaseConnection;
import com.backend.datastores.details.LocalhostMYSQLConnectionDetails;
import com.backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class MysqlUserDao implements UserDao {

    DatabaseConnection conn;

    MysqlUserDao () {
        this.conn = new DatabaseConnection(new LocalhostMYSQLConnectionDetails());
    }

    private Connection getConnection() {
        return this.conn.getConnection();
    }

    @Override
    public User getUser(int id) throws SQLException, NoUserFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM users WHERE id = ?";

        try {
            dbConnection = this.getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return this.filUserBasedOnResultSet(rs);
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        throw new NoUserFoundException("Did not find a user with the id = " + id);
    }

    @Override
    public ArrayList<User> getAllUsers() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();
        try {
            dbConnection = this.getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                users.add(this.filUserBasedOnResultSet(rs));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return users;
    }

    @Override
    public boolean insertUser(User user) throws SQLException {

        String selectSQL =
                "INSERT INTO users ( first_name, surname_prefix, surname, email, password, csrf_token)" +
                "VALUES(?,?,?,?,?,?);";
        return executeUpdate(selectSQL, user);
    }

    private boolean executeUpdate(String selectSQL, User user) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = this.getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            fillFullPreparedStatement(preparedStatement, user);

            // execute select SQL statement
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        String selectSQL =
                "UPDATE users SET first_name = ?, surname_prefix = ?, " +
                        "surname = ?, email = ?, password = ?, csrf_token = ?";
        return executeUpdate(selectSQL, user);
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        String selectSQL =
                "DELETE FROM users WHERE id = " + user.getId();
        return executeUpdate(selectSQL, user);
    }

    @Override
    public User getUserByEmail(String email) throws SQLException, NoUserFoundException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM users WHERE email = ?";

        try {
            dbConnection = this.getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return this.filUserBasedOnResultSet(rs);
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        throw new NoUserFoundException("Did not find a user with the email = " + email);
    }

    private User filUserBasedOnResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setSurnamePrefix(rs.getString("surname_prefix"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCsrfToken(rs.getString("csrf_token"));
        return user;
    }
    private void fillFullPreparedStatement(PreparedStatement preparedStatement, User user)
            throws SQLException {

        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getSurnamePrefix());
        preparedStatement.setString(3, user.getSurname());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getCsrfToken());
    }
}
