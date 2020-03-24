package xyz.perwjs.domain;

/** 购物项代表的是当前书，并表示该书出现了几次. */
public class CartItem {

    private Book book;
    private int quantity;

    //该购物项（书--不一定只有一本）的价钱应该等于书的数量*价格
    private double price;

    //书的价格
    public double getPrice() {
        return book.getPrice() * this.quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
