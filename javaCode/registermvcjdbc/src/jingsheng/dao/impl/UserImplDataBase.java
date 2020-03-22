package jingsheng.dao.impl;

import jingsheng.dao.UserDao;
import jingsheng.domain.User;
import jingsheng.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.Date;

public class UserImplDataBase implements UserDao {
    @Override
    public User find(String username, String password) {

        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM user WHERE username=? AND password=?";

        try {
            User user = (User) queryRunner.query(sql,new BeanHandler(User.class),new Object[]{username,password});
            return user == null ? null : user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("登录失败!");
        }
    }

    @Override
    public void register(User user) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

        String sql = "INSERT INTO user(id,username,password,email,birthday)VALUES(?,?,?,?,?);";

        String id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        Date birthday = user.getBirthday();

        try {
            queryRunner.update(sql,new Object[]{id,username,password,email,birthday});
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("注册失败!");
        }


    }
}
