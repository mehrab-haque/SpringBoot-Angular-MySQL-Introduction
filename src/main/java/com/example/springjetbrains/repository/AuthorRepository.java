package com.example.springjetbrains.repository;

import com.example.springjetbrains.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
