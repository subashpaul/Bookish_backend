package com.myapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.myapp.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
    public Book findByid(int id);
}
