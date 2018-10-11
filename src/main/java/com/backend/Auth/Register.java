package com.backend.Auth;

import com.backend.Auth.CsrfToken.CsrfGenerator;
import com.backend.Auth.Password.PasswordHasher;
import com.backend.dao.DAOFactory;
import com.backend.dao.user.UserDao;
import com.backend.models.User;

import java.sql.SQLException;

public class Register {
    public boolean register(String firstName, String surname_prefix,
                     String surname, String email, String password) {
        // create the required DAO Factory
        DAOFactory UserDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.UserDAOFactory);

        // Create a DAO
        UserDao userDAO = UserDaoFactory.getUserDAO();

        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setSurname(surname);
            user.setSurnamePrefix(surname_prefix);
            user.setEmail(email);
            user.setPassword(new PasswordHasher().hash(password));
            user.setCsrfToken(new CsrfGenerator().generate());
            userDAO.insertUser(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
