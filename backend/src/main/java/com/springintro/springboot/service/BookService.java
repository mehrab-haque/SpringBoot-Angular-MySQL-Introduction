package com.springintro.springboot.service;

import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.model.BookShopModel;
import com.springintro.springboot.repository.BookRepo;
import com.springintro.springboot.repository.BookShopRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class BookService {
    @Autowired
    private BookRepo bookRepository;
    @Autowired
    private BookShopRepo bookShopRepo;

    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();
    }

    public BookModel addNewBook(BookModel new_book){
        return bookRepository.save(new_book);
    }

    public BookModel deleteById(long id){
        Optional<BookModel> bookOptional  = bookRepository.findById(id);


        if (bookOptional.isPresent()) {
            BookModel book = bookOptional.get();

            // Step 2: Remove the book from the set of books associated with the bookshop
            Set<BookShopModel> bookshops = bookShopRepo.findByBooks(book);
            for (BookShopModel bookshop : bookshops) {
                bookshop.getBooks().remove(book);
            }

            // Step 3: Save the updated bookshop entities
            bookRepository.delete(book);
            return book;
        } else {
            // Book not found
            throw new IllegalArgumentException("Book not found with ID: " + id);
        }


    }

    public BookModel updateBook(long id, BookModel old_book) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        book.setAuthor(old_book.getAuthor());
        book.setGenre(old_book.getGenre());
        book.setPrice(old_book.getPrice());
        book.setPublisher(old_book.getPublisher());
        book.setTitle(old_book.getTitle());
        book.setYearOfPublish(old_book.getYearOfPublish());
        return bookRepository.save(book);
    }

    public BookModel getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public List<BookModel> getBooksByIds(List<Long> bookList) {
        return bookRepository.findAllById(bookList);
    }

    public List<BookModel> getBookByTitle(String title) {
        return bookRepository.findByTitleContainsIgnoreCase(title);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }

    public List<BookModel> addAllBooks(List<BookModel> books) {
        return bookRepository.saveAll(books);
    }
}
