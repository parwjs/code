package xyz.perwjs.dao.impl;

import xyz.perwjs.dao.BookDBDao;
import xyz.perwjs.domain.Book;

import java.util.Iterator;
import java.util.Map;

class BookDBDaoImplTest {

    @org.junit.jupiter.api.Test
    void findAll() {
        BookDBDao bookDBDao = new BookDBDaoImpl();
        Map map = bookDBDao.findAll();

        Iterator<Map.Entry<String,Book>> entries = map.entrySet().iterator();

        System.out.println("Map中的内容:");

        while (entries.hasNext()) {
            Map.Entry<String,Book> entry = entries.next();
            System.out.println("key = " + entry.getKey() + ",Value = " + entry.getValue());
        }

    }

    @org.junit.jupiter.api.Test
    void find() {

        BookDBDao bookDBDao = new BookDBDaoImpl();
        Book book = bookDBDao.find("1");
        System.out.println(book);
    }
}