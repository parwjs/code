package xyz.perwjs.service.impl;

import xyz.perwjs.dao.BookDao;
import xyz.perwjs.dao.impl.BookDaoImpl;
import xyz.perwjs.domain.Book;
import xyz.perwjs.domain.Cart;
import xyz.perwjs.exception.CartNotFoundException;
import xyz.perwjs.service.BusinessService;

import java.util.Map;

public class BusinessServiceImpl implements BusinessService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public Map getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book findBook(String id) {
        return bookDao.find(id);
    }

    @Override
    public void BuyBook(String id, Cart cart) {
        Book book = bookDao.find(id);
        cart.addBook(book);
    }

    @Override
    public void deleteBook(String id,Cart cart) {
        //如果用户直接访问DeleteCartBook的Servlet，在Session中没有cart属性
        //告诉用户购物车是空的
        if (cart == null) {
            throw new CartNotFoundException("购物车为空");
        }
        //把购物项移除集合
        cart.getBookMap().remove(id);
    }

    @Override
    public void UpdateQuantity(String id, Cart cart, String quantity) {
        if (cart == null) {
            throw new CartNotFoundException("购物车为空");
        }

        cart.getBookMap().get(id).setQuantity(Integer.parseInt(quantity));
    }

    @Override
    public void clearCart(Cart cart) {
        if (cart == null) {
            throw new CartNotFoundException("购物车为空");
        }
        cart.getBookMap().clear();
    }
}
