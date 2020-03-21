package test;

import jingsheng.dao.impl.registerImpl;
import jingsheng.domain.User;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class registerImplTest {

    private String username = "jingsheng";
    private String password = "123";

    @org.junit.jupiter.api.Test
    void testfind() {

        registerImpl register = new registerImpl();
        User user = register.find(username,password);
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getBirthday());

    }

    @org.junit.jupiter.api.Test
    void testregiste() {
        registerImpl register = new registerImpl();

        User user = new User();
        user.setId("2");
        user.setUsername("kangkang");
        user.setPassword("123");
        user.setEmail("kk@163.com");
        user.setBirthday(new Date());
        register.registe(user);
    }


    @org.junit.jupiter.api.Test
    void testUId() {
        System.out.println(UUID.randomUUID());
    }
}