package com.springintro.springboot.controller;


import com.springintro.springboot.model.AuthorModel;
import com.springintro.springboot.model.BookArrayModel;
import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.model.BookShopModel;
import com.springintro.springboot.service.BookService;
import com.springintro.springboot.service.BookShopService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class BookShopController {

    @Autowired
    private BookShopService bookShopService;
    @Autowired
    private BookService bookService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/shops")
    public BookShopModel addNewBookShop(@RequestBody BookShopModel new_bookShop){
        return bookShopService.addNewShop(new_bookShop);
    }


    @GetMapping("/shops")
    public List<BookShopModel>  getAllShops(){
        return bookShopService.getAll();
    }

    @GetMapping("/shops/")
    public List<BookShopModel> getShopByTitle(@RequestParam("title") String title){
        return bookShopService.getShopByTitle(title);
    }

    @GetMapping("/shops/{id}")
    public BookShopModel getShopById(@PathVariable("id") long id){
        return bookShopService.getShopById(id);
    }

    @PostMapping("/shops/books/{id}")
    public ResponseEntity<BookShopModel> addBooks(@PathVariable("id") long id,@RequestBody BookArrayModel books_list){

        BookShopModel shop = bookShopService.getShopById(id);
        if (shop != null) {
            List<BookModel> books = bookService.getBooksByIds(books_list.getIds());
            if (!books.isEmpty()) {
                shop.getBooks().addAll(books);
                BookShopModel updatedShop = bookShopService.addNewShop(shop);

                return ResponseEntity.ok(updatedShop);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/shops/{id}")
    public BookShopModel deleteShop(@PathVariable("id") long id) {
        return bookShopService.deleteById(id);
    }
    @PutMapping("/shops/{id}")
    public BookShopModel updateBookShop(@PathVariable("id") long id,@RequestBody BookShopModel shop){
        return bookShopService.updateBookShop(id,shop );
    }
}
