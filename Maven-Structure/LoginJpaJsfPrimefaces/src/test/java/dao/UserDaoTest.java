package dao;

import static org.mockito.Mockito.when;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import model.User;

public class UserDaoTest {
	
	@Mock
    UserDao uDao;
	
	@Mock
	private EntityManagerFactory emf;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query q;
	
	@Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(emf.createEntityManager()).thenReturn(em);
        when(em.createNamedQuery(Mockito.anyString())).thenReturn(q);
        
    }
	
	@Ignore
	@Test
	public void testGetUserByUsername(){
		when(emf.createEntityManager()).thenReturn(em);
		 User u = uDao.getUserByUsername("user");
		 Assert.assertNull(u);
	}
	@Ignore
	@Test
	public void testInsertUserToDB(){
		//uDao.insertUserToDB("user", "pass"));
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Assert.assertEquals(1, invocation.getArguments().length);
				System.out.println("test");
				return null;
			}
		}).when(em).persist(Matchers.any(User.class));
	}
	
}
