package xyz.perwjs.dao;

import xyz.perwjs.domain.Book;

import java.util.Map;

public interface BookDao {

    //获取存放着书籍的Map集合
    Map getAll();

    //根据关键字获取某本书籍
    Book find(String id);




}
