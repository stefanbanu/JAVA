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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

/**
 *
 * @author stefanbanu
 */
public class UserDaoTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStatement;
    @Mock
    private ResultSet mockResultSet;
    
    @InjectMocks
    UserDao uDao;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getString("username")).thenReturn("user");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loginUser method, of class UserDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testLoginUser() throws SQLException {
        Mockito.when(mockResultSet.getString("password")).thenReturn("12345");        
        String result = uDao.loginUser("user", "12345");
        assertEquals("user", result);            
    }

    /**
     * Test of registerUser method, of class UserDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRegisterUser() throws SQLException {   
        String result = uDao.registerUser("user", "testPass");
        assertEquals("user", result);              
    }
    
    @Test
    public void testInserUser() throws SQLException { 
        uDao.registerUser("wrongUser", "test");
        OngoingStubbing<Integer> obj = Mockito.when(mockStatement.executeUpdate());
        assertNotNull(obj);
    }
}
