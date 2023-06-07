package com.example.jdk17test.service;

import com.example.jdk17test.entity.Author;
import com.example.jdk17test.entity.Book;
import com.example.jdk17test.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }
    public Set<Author> saveAuthors(Set<Author> authors){
        return authorRepository.saveAll(authors).stream().collect(Collectors.toSet());
    }
    public Set<Author>  getAllAuthors(){
        return authorRepository.findAll().stream().collect(Collectors.toSet());
    }
    public Author getAuthorById(Long authorId){
        return authorRepository.findById(authorId).get();
    }
//    public String deleteAuthor(Long authorId){
//        authorRepository.deleteById(authorId);
//        return "Author with id "+authorId+" removed\n";
//    }
    public ResponseEntity<Map<String, Boolean>> deleteAuthor(Long authorId){
        authorRepository.deleteById(authorId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    public Author updateAuthor(Author author, Long authorId){
        Author prev=authorRepository.findById(authorId).orElse(null);
        prev.setAuthorName(author.getAuthorName());
        prev.setBooks(author.getBooks());
        return authorRepository.save(prev);
    }

    public Set<Book> getBooks(Long authorId){
        Author author=authorRepository.findById(authorId).get();
        return author.getBooks();
    }

    public Set<Author>getAuthorsByBookId(Long bookId){
        return authorRepository.getAuthorsByBookId(bookId);
    }
}
