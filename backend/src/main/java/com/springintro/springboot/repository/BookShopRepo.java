package com.springintro.springboot.repository;

import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.model.BookShopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookShopRepo extends JpaRepository<BookShopModel, Long> {
    List<BookShopModel> findByTitleContainsIgnoreCase(String title);

    Set<BookShopModel> findByBooks(BookModel book);
    //
}
