package com.myapp.myapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.entities.Book;
import com.myapp.service.BookService;

@RestController
public class controller {

    @Autowired
    private BookService bookService;

    @CrossOrigin(origins = "*")
    @GetMapping("/books")
    public ResponseEntity<List<Book>> test() {
        List<Book> books = this.bookService.getAllBooks();
        if (books.size() != 0) {
            return ResponseEntity.of(Optional.of(books));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = this.bookService.getBookbyId(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        // TODO: process POST request
        try {

            b = this.bookService.addBook(book);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        try {
            this.bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        try {

            return ResponseEntity.ok().body(bookService.updateBook(book, id));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
