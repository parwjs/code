package jingsheng.dao;

import jingsheng.domain.User;

public interface registerDao {

    //在XML中查找该该数据，并返回User对象
    User find(String username,String password);

    //注册功能
    void registe(User user);

}
