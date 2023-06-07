package com.example.jdk17test.controller;

import com.example.jdk17test.entity.Book;
import com.example.jdk17test.entity.BookShop;
import com.example.jdk17test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping( "api/books")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public Book addBook(@RequestBody Book book){return bookService.saveBook(book);}
    @GetMapping
    public Set<Book> findAllBooks(){
        return bookService.getBooks();
    }
    @GetMapping(path = "/id/{bookId}")
    public Book findBookByTd(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }
    @GetMapping(path = "/title/{title}")
    public Set<Book> findBookByTitle(@PathVariable String title){
        return bookService.getBookByTitle(title);
    }
    @PutMapping(path = "/{bookId}")
    public Book updateBook(@PathVariable Long bookId,@RequestBody Book book){return bookService.updateBook(bookId,book);}
    @DeleteMapping(path = "/{bookId}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long bookId){
        return bookService.deleteBook(bookId);
    }
    @DeleteMapping
    public ResponseEntity<Map<String, Boolean>> deleteAllBooks(){
        return bookService.deleteAllBooks();
    }
//    @DeleteMapping(path = "/{bookId}")
//    public String deleteBook(@PathVariable Long bookId){
//        return bookService.deleteBook(bookId);
//    }
//    @DeleteMapping
//    public String deleteAllBooks(){
//        return bookService.deleteAllBooks();
//    }
    @GetMapping(path = "/shop/{bookId}")
    public BookShop getShop(@PathVariable Long bookId){
        return bookService.getShopId(bookId);
    }
    @PutMapping(path = "/{bookId}/author/{authorID}")
    public Book assignAuthor(@PathVariable Long bookId,@PathVariable Long authorID){return bookService.assignAuthor(bookId,authorID);}
    @GetMapping(path = "/author/{authorId}")
    public Set<Book> findBooksByAuthorId(@PathVariable Long authorId) {return bookService.getBooksByAuthorId(authorId);}
    @GetMapping(path = "/bookshop/{shopId}")
    public Set<Book> findBooksByBookShopId(@PathVariable Long shopId) {return bookService.getBooksByBookShopId(shopId);}
}
