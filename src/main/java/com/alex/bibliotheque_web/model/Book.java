package com.alex.bibliotheque_web.model;


public class Book {
    private String title;
    private String author;
    private int stock;
    private int book_id;
    private String img_link;

    public Book(String title, String author, int stock, int book_id, String img_link) {
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.book_id = book_id;
        this.img_link = img_link;
    }

    public Book() {}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getStock() {
        return stock;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }
}
