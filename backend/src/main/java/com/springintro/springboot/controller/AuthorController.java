package com.springintro.springboot.controller;


import com.springintro.springboot.model.AuthorModel;
import com.springintro.springboot.model.BookArrayModel;
import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.service.AuthorService;
import com.springintro.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @PostMapping("/authors")
    public AuthorModel addAuthor(@RequestBody AuthorModel author){
        return authorService.addAuthor(author);
    }

    @GetMapping("/authors")
    public List<AuthorModel> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorModel> getAuthorById(@PathVariable("id") long id) {
        AuthorModel author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable("id") long id, @RequestBody AuthorModel updatedAuthor) {
        AuthorModel author = authorService.getAuthorById(id);
        if (author != null) {
            updatedAuthor.setId(author.getId()); // Ensure the ID remains the same
            AuthorModel savedAuthor = authorService.addAuthor(updatedAuthor); // Update the author
            return ResponseEntity.ok(savedAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/authors/{id}")
    public AuthorModel deleteAuthor(@PathVariable("id") long id) {
       return authorService.deleteAuthorById(id);

    }

    @PutMapping("/authors/book/{id}")
    public ResponseEntity<AuthorModel> updateAuthorBook(@PathVariable("id") long id, @RequestBody BookArrayModel bookList) {
        AuthorModel author = authorService.getAuthorById(id);
        if (author != null) {
            List<BookModel> books = bookService.getBooksByIds(bookList.getIds());

            if (!books.isEmpty()) {
                author.getBooks().addAll(books);
                AuthorModel updatedAuthor = authorService.addAuthor(author);
                return ResponseEntity.ok(updatedAuthor);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
