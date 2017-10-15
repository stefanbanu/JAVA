/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import com.model.Users;
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
        emf = Persistence.createEntityManagerFactory("LoginJpaJsfPrimefacesPU");
    }

    public Users getUserByUsername(String username) {

        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Users.findByUsername");
            q.setParameter("username", username);
            List<Users> list = q.getResultList();
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
            Users u = new Users();
            u.setUsername(username);
            u.setPassword(password);
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public boolean checkUser(String username) {
        Users u = getUserByUsername(username);
        return u != null;
    }
}
