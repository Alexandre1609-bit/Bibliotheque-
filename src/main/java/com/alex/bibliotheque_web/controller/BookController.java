package com.alex.bibliotheque_web.controller;


import com.alex.bibliotheque_web.dao.BookDAO;
import com.alex.bibliotheque_web.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookDAO bookDAO;

    public BookController (BookDAO bookDAO) {this.bookDAO = bookDAO;}

    @GetMapping("/livres")
    public List<Book> getList() {
        return bookDAO.findAll();
    }

}
