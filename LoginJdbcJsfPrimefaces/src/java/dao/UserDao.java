/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefanbanu
 */
public class UserDao {

    private final Connection con;
    private static final String SQL = "SELECT * FROM users";
    private static final String INSERT = "INSERT INTO users (id,username, password) VALUES(NULL,?,?)";

    public UserDao(Connection con) {
        this.con = con;
    }

    public String loginUser(String username, String password) {
        try (PreparedStatement stmt = con.prepareStatement(SQL);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                    return username;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }   

    public String registerUser(String username, String password) {
        if (checkUser(username)) {
            return username;
        }
        insertUser(username, password);
        return "";
    }

    private boolean checkUser(String username) {
        try (final PreparedStatement stmt = con.prepareStatement(SQL);final ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("username").equals(username)) {
                    return true;
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void insertUser(String username, String password) {
        try (PreparedStatement stmt = con.prepareStatement(INSERT)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
