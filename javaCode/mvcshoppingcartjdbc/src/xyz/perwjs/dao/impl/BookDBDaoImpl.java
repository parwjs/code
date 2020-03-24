package xyz.perwjs.dao.impl;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import xyz.perwjs.dao.BookDBDao;
import xyz.perwjs.domain.Book;
import xyz.perwjs.utils.DBUtils;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class BookDBDaoImpl implements BookDBDao {
    @Override
    public Map findAll() {
        Map<String,Book> map = new LinkedHashMap<>();

        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

        String sql = "SELECT id FROM products";


        try {
            List<Map<String,Object>> list = queryRunner.query(sql,new MapListHandler());
            for (Map<String,Object> maps : list) {
                for (String key : maps.keySet()) {
                    /*System.out.print("key = " + key);
                    System.out.print(" value = " + maps.get(key));
                    System.out.println();*/
                    String idValue = (String) maps.get(key);
                    Book book = find(idValue);
                    map.put(idValue,book);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Book find(String id) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM products WHERE id=?";

        try {
            Book book = (Book) queryRunner.query(sql,new BeanHandler(Book.class),new Object[]{id});
            return book == null ? null : book;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("没有此书!");
        }

    }
}
