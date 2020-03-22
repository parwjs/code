package jingsheng.dao;

import jingsheng.domain.User;

public interface UserDao {

    User find(String username,String password);

    void register(User user);
}
