package com.springintro.springboot.controller;

import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class BookController {

    @Autowired
    private  BookService bookService;

    @GetMapping("/books")
    public List<BookModel> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/")
    public List<BookModel> getBookByTitle(@RequestParam("title") String title){
        return bookService.getBookByTitle(title);
    }
    @GetMapping("/books/{id}")
    public BookModel getBookById(@PathVariable("id") long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/books/")
    public BookModel addNewBook(@RequestBody BookModel new_book){
        return bookService.addNewBook(new_book);
    }

    @PostMapping("/books/all")
    public List<BookModel> addAllBooks(@RequestBody List<BookModel> books){
        return bookService.addAllBooks(books);
    }

    @DeleteMapping("/books/{id}")
    public BookModel deleteBook(@PathVariable("id") long id) {
        return bookService.deleteById(id);
    }
    @PutMapping("/books/{id}")
    public BookModel updateBook(@PathVariable("id") long id,@RequestBody BookModel book){
        return bookService.updateBook(id,book );
    }

    @DeleteMapping("/books")
    public void deleteAllBook() {
        bookService.deleteAll();
    }


}
