package com.example.springjetbrains.controller;

import com.example.springjetbrains.entity.Book;
import com.example.springjetbrains.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping("/book/add")
    public Book addBook(@RequestBody Book book){
        return service.saveBook(book);
    }

    @GetMapping("/book/list")
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }
}
