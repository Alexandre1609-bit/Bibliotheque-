package com.alex.bibliotheque_web.service;
import com.alex.bibliotheque_web.dao.BookDAO;
import com.alex.bibliotheque_web.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookDAO bookDAO;

    public BookService (BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void getList() {
        bookDAO.findAll();
    }

    public Book findBookById(int id) {
        return bookDAO.getBookByID(id);
    }
}

