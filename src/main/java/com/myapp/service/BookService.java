package com.myapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.dao.BookRepository;
import com.myapp.entities.Book;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    public Book getBookbyId(int id) {
        Book book = null;
        try {
            book = this.bookRepository.findByid(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book b) {
        Book result = this.bookRepository.save(b);
        return result;

    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book, int bookId) {
        book.setId(bookId);
        bookRepository.save(book);
        return getBookbyId(bookId);
    }
}
