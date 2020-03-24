package xyz.perwjs.domain;

public class Book {

    private String id;
    private String name;
    private String author;
    private double price;
    private String description;


/*    public Book(String id,String name,String author,String description,double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
    }*/


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
