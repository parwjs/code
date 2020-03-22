package jingsheng.util;

import jingsheng.dao.UserDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static UserDao userDao = null;
    private DaoFactory(){

        try {
            //读取配置文件信息
            InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("UserDao.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String userClass = properties.getProperty("userClass");

            //反射
            userDao = (UserDao) Class.forName(userClass).newInstance();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }



    }

    public static UserDao createUserDao() {
        return userDao;
    }


    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    public  static DaoFactory newInstance() {
        return DAO_FACTORY;
    }


}
