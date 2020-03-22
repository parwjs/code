package jingsheng.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {

    private static ComboPooledDataSource comboPooledDataSource = null;

    static {
        //自动寻找配置文件，节点为mysql的数据库【如果没有，就使用默认的】
        comboPooledDataSource = new ComboPooledDataSource("mysql");
    }

    public static DataSource getDataSource() {
        return comboPooledDataSource;
    }

    public static Connection getConnection() {
        try {
            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库初始化失败!");
        }
    }
}
