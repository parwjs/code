package test;

import jingsheng.dao.UserDao;
import jingsheng.dao.impl.UserImplDataBase;
import jingsheng.dao.impl.UserImplXML;
import jingsheng.domain.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

class registerImplTest {

    private String username = "jingsheng";
    private String password = "123";

    @org.junit.jupiter.api.Test
    void testfind() {

        UserImplXML register = new UserImplXML();
        User user = register.find(username,password);
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getBirthday());

    }

    @org.junit.jupiter.api.Test
    void testregiste() throws ParseException {
        UserDao register = new UserImplDataBase();

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("dakls");
        user.setPassword("123456");
        user.setEmail("aopsdk@184.com");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        Date birthday = simpleDateFormat.parse("1999-01-01");
        user.setBirthday(birthday);
        register.register(user);
    }


    @org.junit.jupiter.api.Test
    void testUId() {
        System.out.println(UUID.randomUUID().toString().length());
    }
}