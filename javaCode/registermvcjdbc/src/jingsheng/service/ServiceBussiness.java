package jingsheng.service;

import jingsheng.domain.User;

public interface ServiceBussiness {
    User login(String username,String password);

    void register(User user);
}
