package com.example.springjetbrains.repository;

import com.example.springjetbrains.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
