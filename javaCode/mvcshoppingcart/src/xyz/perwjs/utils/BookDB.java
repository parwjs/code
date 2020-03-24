package xyz.perwjs.utils;

import xyz.perwjs.domain.Book;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookDB {
    private static Map<String, Book> map = new LinkedHashMap<>();

    static {
        map.put("1",new Book("1","java","jingsheng","web",99));
        map.put("2",new Book("2","javaweb","dsak","just so so",22));
        map.put("3",new Book("3","ajax","xiaohong","good",88));
        map.put("4",new Book("4","python","xiaogou","no",20));
    }

    public static Map<String,Book> getAll() {
        return map;
    }
}
