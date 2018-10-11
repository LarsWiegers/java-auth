package com.backend.Auth.Password;

import com.backend.dao.DAOFactory;
import com.backend.dao.user.NoUserFoundException;
import com.backend.dao.user.UserDao;
import com.backend.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class Authenticater {
    public boolean passwordMatches(String email, String password) {

        // create the required DAO Factory
        DAOFactory UserDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.UserDAOFactory);

        // Create a DAO
        UserDao userDAO = UserDaoFactory.getUserDAO();
        User user;
        try {
            user = userDAO.getUserByEmail(email);
        } catch (SQLException | NoUserFoundException e) {
            e.printStackTrace();
            return false;
        }
        String hashed = user.getPassword();

        return BCrypt.checkpw(password, hashed);

    }
}
