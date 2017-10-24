package service;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dao.UserDao;
import model.Car;
import model.User;
 
@ManagedBean(name = "userService")
@ApplicationScoped
public class UserServiceDisplay implements Serializable{
	
	private final UserDao uDao;

	public UserServiceDisplay() {
		uDao = new UserDao();
	}
     
	public List<User> getUsers() {
		return uDao.getAllUsers();
	}

}