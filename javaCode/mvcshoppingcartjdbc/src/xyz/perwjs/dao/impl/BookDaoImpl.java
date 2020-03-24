package xyz.perwjs.dao.impl;

import xyz.perwjs.dao.BookDao;
import xyz.perwjs.domain.Book;
import xyz.perwjs.utils.BookDB;

import java.util.Map;

public class BookDaoImpl implements BookDao {
    @Override
    public Map getAll() {
        return BookDB.getAll();
    }

    @Override
    public Book find(String id) {
        return BookDB.getAll().get(id);
    }
}
