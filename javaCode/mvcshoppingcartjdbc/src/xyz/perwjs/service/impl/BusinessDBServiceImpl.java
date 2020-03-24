package xyz.perwjs.service.impl;

import xyz.perwjs.dao.BookDBDao;
import xyz.perwjs.dao.impl.BookDBDaoImpl;
import xyz.perwjs.domain.Book;
import xyz.perwjs.domain.Cart;
import xyz.perwjs.exception.CartNotFoundException;
import xyz.perwjs.service.BusinessService;

import java.util.Map;

public class BusinessDBServiceImpl implements BusinessService {

    BookDBDao bookDBDao = new BookDBDaoImpl();

    @Override
    public Map getAll() {
        return bookDBDao.findAll();
    }

    @Override
    public Book findBook(String id) {
        return bookDBDao.find(id);
    }

    @Override
    public void BuyBook(String id, Cart cart) {
        Book book = bookDBDao.find(id);
        cart.addBook(book);
    }

    @Override
    public void deleteBook(String id, Cart cart) {

        if (cart == null) {
            throw new CartNotFoundException("购物车为空");
        }

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
