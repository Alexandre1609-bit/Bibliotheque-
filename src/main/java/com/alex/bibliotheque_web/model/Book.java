package com.alex.bibliotheque_web.model;


public class Book {
    private String title;
    private String author;
    private Integer stock;
    private Integer book_id;
    private String img_link;
    private String summary;

    public Book(String title, String author, Integer stock, Integer book_id, String img_link, String summary) {
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.book_id = book_id;
        this.img_link = img_link;
        this.summary = summary;
    }

    public Book() {}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public String getImg_link() {
        return img_link;
    }

    public String getSummary() { return summary;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
