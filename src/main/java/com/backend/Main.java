package com.backend;

import com.backend.Auth.Login;
import com.backend.Auth.Password.PasswordHasher;
import com.backend.Auth.Register;
import com.backend.dao.DAOFactory;
import com.backend.dao.user.NoUserFoundException;
import com.backend.dao.user.UserDao;
import com.backend.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Login login = new Login();
        System.out.println(
                login.login( "test@gmail.com",
                        "test123",
                        "ed85f9eb-d137-4a2d-8b53-bc757f14a801")
        );

        // create the required DAO Factory
        DAOFactory UserDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.UserDAOFactory);

        // Create a DAO
        UserDao userDAO = UserDaoFactory.getUserDAO();

        try {
            System.out.println(userDAO.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

