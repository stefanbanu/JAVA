/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import model.User;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author stefanbanu
 */
public class UserDao implements Serializable{

	private static final long serialVersionUID = 1L;
	private final transient EntityManagerFactory emf;

	public UserDao() {
		emf = Persistence.createEntityManagerFactory("LoginJpaJsfPrimefaces");
	}

	public User getUserByUsername(String username) {

		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createNamedQuery("Users.findByUsername");
			q.setParameter("username", username);
			List<User> list = q.getResultList();
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		} finally {
			em.close();
		}
	}

	public void insertUserToDB(String username, String password) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			User u = new User();
			u.setUsername(username);
			u.setPassword(password);
			em.persist(u);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public boolean checkUser(String username) {
		User u = getUserByUsername(username);
		return u != null;
	}

	public List<User> getAllUsers() {
		List<User> list = null;
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createNamedQuery("User.findAll");
			list = q.getResultList();

		} finally {
			em.close();
		}
		return list;
	}
}
