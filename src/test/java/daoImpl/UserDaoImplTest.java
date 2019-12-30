package daoImpl;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    UserDaoImpl dao;
    @BeforeEach
    void setUp() {
        dao=new UserDaoImpl();
    }

    @AfterEach
    void tearDown() {
        dao=null;
    }

    @Disabled
    @Test
    void register() {
        System.out.println(dao.register("guagua","123").toString());
    }

    @Disabled
    @Test
    void login() {
        User user=dao.login("dudu","111");
//        assertEquals("dudu",user.getUser_name());
//        assertEquals("123",user.getUser_password());
//        assertEquals(2,user.getUser_id());
//        assertEquals(null,user);
        System.out.println(user.getUser_name());
    }
}