/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefanbanu
 */
public enum UserService {
    INSTANCE;
    
    private Connection con;
    private UserDao uDao;

    private UserService() {
        connectToDB();
    }

    private void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://localhost/mydatabase";
            String user = "user1";
            String pass = "12345";
            con = DriverManager.getConnection(url, user, pass);
            uDao = new UserDao(con);
        } catch (SQLException  | ClassNotFoundException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String login(String username, String password){
        String res = uDao.loginUser(username, password);        
        return res;
    }
    
    public String register(String username, String password){
        String res = uDao.registerUser(username, password);     
        return res;
    }
     
}
