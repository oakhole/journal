package com.oakhole.auth.service;

import com.oakhole.auth.entity.User;
import com.oakhole.auth.repository.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @since 14-3-18
 */
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private BusinessLogger businessLogger;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ShiroTestUtils.mockSubject(new ShiroDbRealm.ShiroUser("foo", "foo"));
    }

    @After
    public void tearDown() throws Exception {
        ShiroTestUtils.clearSubject();
    }

    @Test
    public void testSaveUser() throws Exception {
        User guest = new User();
        guest.setId(1L);
        guest.setUsername("guest");
        guest.setPlainPassword("guest");

        this.userService.saveUser(guest);

        System.out.println(guest.getPassword() + " " + guest.getSalt());

        Mockito.verify(userDao,Mockito.never()).delete(1L);
    }
}
