package service;
 
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dao.UserDao;
import model.User;
 
@ManagedBean(name = "userService")
@ApplicationScoped
public class UserServiceDisplay implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final UserDao uDao;

	public UserServiceDisplay() {
		uDao = new UserDao();
	}
     
	public List<User> getUsers() {
		return uDao.getAllUsers();
	}

}