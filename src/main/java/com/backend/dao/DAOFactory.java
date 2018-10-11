package com.backend.dao;

import com.backend.dao.user.UserDao;
import com.backend.dao.user.UserDaoFactory;

// Abstract class DAO Factory
public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int UserDAOFactory = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract UserDao getUserDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case UserDAOFactory:
                return new UserDaoFactory();
            default:
                return null;
        }
    }
}