package com.springintro.springboot.service;

import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.model.BookShopModel;
import com.springintro.springboot.repository.BookRepo;
import com.springintro.springboot.repository.BookShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookShopService {

    @Autowired
    private BookShopRepo bookShopRepo;
    @Autowired
    private BookRepo bookRepo;

    public BookShopModel addNewShop(BookShopModel newShop){
        return bookShopRepo.save(newShop);
    }


    public List<BookShopModel> getAll() {
        return bookShopRepo.findAll();
    }

    public BookShopModel getShopById(long id) {
        return bookShopRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public BookShopModel addBookToShop(long id, List<Long> books_id) {
        BookShopModel shop = bookShopRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shop Id:" + id));
        Set<BookModel> books = new HashSet<>();
        for (Long l :books_id) {
            BookModel book = bookRepo.findById(l)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
            books.add(book);
        }
        shop.setBooks(books);
        return shop;

    }

    public BookShopModel deleteById(long id) {
        BookShopModel shop = bookShopRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shop Id:" + id));
        bookShopRepo.delete(shop);
        return shop;
    }

    public List<BookShopModel> getShopByTitle(String title) {
        return bookShopRepo.findByTitleContainsIgnoreCase(title);
    }

    public BookShopModel updateBookShop(long id, BookShopModel old_shop) {

        BookShopModel shop = bookShopRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid shop Id:" + id));

        shop.setTitle(old_shop.getTitle());
        shop.setEmail(old_shop.getEmail());
        shop.setLocation(old_shop.getLocation());
        shop.setContactNo(old_shop.getContactNo());
        shop.setBooks(old_shop.getBooks());
        return bookShopRepo.save(shop);
    }
}
