package xyz.perwjs.dao;

import xyz.perwjs.domain.Book;

import java.util.Map;

public interface BookDBDao {

    Map findAll();


    Book find(String id);

}
