package com.example.jdk17test.service;

import com.example.jdk17test.entity.Book;
import com.example.jdk17test.entity.BookShop;
import com.example.jdk17test.repository.BookRepository;
import com.example.jdk17test.repository.BookShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookShopService {
    @Autowired
    private BookShopRepository bookShopRepository;
    @Autowired
    BookRepository bookRepository;
    public BookShop saveShop(BookShop bookShop){
        return bookShopRepository.save(bookShop);
    }
    public Set<BookShop> saveBookShops(Set<BookShop> bookShops){
        return bookShopRepository.saveAll(bookShops.stream().collect(Collectors.toList())).stream().collect(Collectors.toSet());
    }
    public Set<BookShop>  getBookShops(){
        return bookShopRepository.findAll().stream().collect(Collectors.toSet());
    }
    public BookShop getBookShopById(Long shopID){
        return bookShopRepository.findById(shopID).get();
    }
   public Set<BookShop> getShopByName(String shopName){return bookShopRepository.findBookShopByShopName(shopName);}
//    public String deleteBookShop(Long shopId){
//        bookShopRepository.deleteById(shopId);
//        return "BookShop with id "+shopId+" removed\n";
//    }
    public ResponseEntity<Map<String, Boolean>> deleteBookShop(Long shopId){
        bookShopRepository.deleteById(shopId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    public BookShop updateBookShop(Long shopId, BookShop bookShop){
        BookShop prev=bookShopRepository.findById(shopId).orElse(null);
        prev.setLocation(bookShop.getLocation());
        prev.setEmail(bookShop.getEmail());
        prev.setContactNo(bookShop.getContactNo());
        prev.setPrice(bookShop.getPrice());
        prev.setShopName(bookShop.getShopName());
        prev.setYearOfPublish(bookShop.getYearOfPublish());
        return bookShopRepository.save(prev);
    }
//    public String deleteAllBookShops() {
//        bookShopRepository.deleteAll();
//        return "All bookShops removed\n";
//    }
    public ResponseEntity<Map<String, Boolean>> deleteAllBookShops(){
        bookShopRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    public BookShop assignShop(Long bookId, Long shopId) {
    Book book=bookRepository.findById(bookId).get();
    BookShop bookShop =bookShopRepository.findById(shopId).get();
    //bookShop.getBooks().add(book);
    book.setBookShop(bookShop);
    System.out.println("succcessfully called");
    return bookShopRepository.save(bookShop);
    }
}
