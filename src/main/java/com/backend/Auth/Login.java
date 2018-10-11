package com.backend.Auth;

import com.backend.Auth.CsrfToken.CsrfGenerator;
import com.backend.Auth.Password.Authenticater;
import com.backend.Auth.Password.PasswordHasher;
import com.backend.dao.DAOFactory;
import com.backend.dao.user.NoUserFoundException;
import com.backend.dao.user.UserDao;
import com.backend.models.User;

import java.sql.SQLException;

public class Login {
    public boolean login(String email, String password, String csrfToken) {

        Authenticater authenticator = new Authenticater();
        if(!authenticator.passwordMatches(email,password) ) {
            System.out.println("HI");
            return false;
        }



        // create the required DAO Factory
        DAOFactory UserDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.UserDAOFactory);

        // Create a DAO
        UserDao userDAO = UserDaoFactory.getUserDAO();
        User user;
        try {
            user = userDAO.getUserByEmail(email);

            if(!user.getCsrfToken().equals(csrfToken) ) {
                return false;
            }

        } catch (SQLException | NoUserFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
