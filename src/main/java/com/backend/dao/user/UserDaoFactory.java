package com.backend.dao.user;

import com.backend.dao.DAOFactory;

public class UserDaoFactory extends DAOFactory {

    public UserDao getUserDAO() {
        return new MysqlUserDao();
    }
}
