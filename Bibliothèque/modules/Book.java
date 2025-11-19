package modules;


public class Book {
    String title;
    String author;
    int stock;
    int book_id;

    public Book(String title, String author, int stock, int book_id) {
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.book_id = book_id;
    }
}
