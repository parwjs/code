package xyz.perwjs.service;

import xyz.perwjs.domain.Book;
import xyz.perwjs.domain.Cart;

import java.util.Map;

public interface BusinessService {
    Map getAll();

    Book findBook(String id);


    void BuyBook(String id, Cart cart);


    //删除购买的书籍
    void deleteBook(String id,Cart cart);

    void UpdateQuantity(String id,Cart cart,String quantity);

    //清空购物车
    void clearCart(Cart cart);

}
