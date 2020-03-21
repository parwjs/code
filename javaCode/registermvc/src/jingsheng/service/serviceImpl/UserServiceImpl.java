package jingsheng.service.serviceImpl;

import jingsheng.dao.impl.registerImpl;
import jingsheng.domain.User;
import jingsheng.service.ServiceBussiness;

public class UserServiceImpl implements ServiceBussiness {

    registerImpl register = new registerImpl();

    @Override
    public User login(String username, String password) {

        return register.find(username,password);
    }

    @Override
    public void register(User user) {

        register.registe(user);
    }
}
