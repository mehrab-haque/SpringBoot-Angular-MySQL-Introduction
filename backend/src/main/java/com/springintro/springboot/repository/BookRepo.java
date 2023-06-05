package com.springintro.springboot.repository;

import com.springintro.springboot.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepo extends JpaRepository<BookModel, Long> {
    List<BookModel> findByTitleContainsIgnoreCase(String title);
    //
}
