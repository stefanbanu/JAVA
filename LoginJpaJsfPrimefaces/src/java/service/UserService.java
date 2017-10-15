/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import com.model.Users;

/**
 *
 * @author stefanbanu
 */
public enum UserService {
    INSTANCE;
    private final UserDao uDao;

    private UserService() {
        uDao = new UserDao();
    }

    public String register(String username, String password) {
        if (uDao.checkUser(username)) {
            return username;
        }
        uDao.insertUserToDB(username, password);
        return "";
    }

    public Users login(String username) {
        return uDao.getUserByUsername(username);      
    }
}
