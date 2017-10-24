package service;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.User;

 
@ManagedBean
public class UserExporterView implements Serializable {
     
	private static final long serialVersionUID = 1L;
	private List<User> users;
         
    public List<User> getUsers() {
		return users;
	}


	@ManagedProperty("#{userService}")
    private UserServiceDisplay service;
     
    @PostConstruct
    public void init() {
        users = service.getUsers();
    }
 
 
    public void setService(UserServiceDisplay service) {
        this.service = service;
    }
}