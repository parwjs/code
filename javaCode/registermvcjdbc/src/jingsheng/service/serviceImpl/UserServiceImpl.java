package jingsheng.service.serviceImpl;

import jingsheng.dao.UserDao;
import jingsheng.dao.impl.UserImplXML;
import jingsheng.domain.User;
import jingsheng.service.ServiceBussiness;
import jingsheng.util.DaoFactory;

public class UserServiceImpl implements ServiceBussiness {

//    UserImplXML register = new UserImplXML();
    UserDao register = DaoFactory.newInstance().createUserDao();

    @Override
    public User login(String username, String password) {

        return register.find(username,password);
    }

    @Override
    public void register(User user) {

        register.register(user);
    }
}
