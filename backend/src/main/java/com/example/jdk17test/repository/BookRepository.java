package com.example.jdk17test.repository;

import com.example.jdk17test.entity.Book;
import com.example.jdk17test.entity.BookShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book,Long> {
    Set<Book> findByTitle(String title);
    @Query(value = "select b from Book b inner join b.authors a  where a.authorId =:authorId" )
    Set<Book> getBooksByAuthorId(@Param("authorId") Long authorId);
    @Query(value = "select b from Book b inner join b.bookShop a  where a.shopId =:shopId" )
    Set<Book> getBooksByShopId(Long shopId);
    @Query(value = "select b.bookShop from Book b  where b.bookId =:bookId" )
    BookShop getShopByBookId(Long bookId);
}
