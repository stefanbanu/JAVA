/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import model.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author stefanbanu
 */
public class UserDao {

    private final EntityManagerFactory emf;

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
}
