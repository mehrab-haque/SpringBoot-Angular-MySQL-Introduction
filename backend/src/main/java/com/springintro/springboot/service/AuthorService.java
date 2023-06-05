package com.springintro.springboot.service;

import com.springintro.springboot.model.AuthorModel;
import com.springintro.springboot.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public AuthorModel addAuthor(AuthorModel author) {
        return authorRepo.save(author);
    }

    public List<AuthorModel> getAllAuthors() {
        return authorRepo.findAll();
    }

    public AuthorModel getAuthorById(long id) {
        return authorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public AuthorModel deleteAuthorById(long id) {
        AuthorModel author = authorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid auth Id:" + id));
        authorRepo.delete(author);
        return author;

    }
}
