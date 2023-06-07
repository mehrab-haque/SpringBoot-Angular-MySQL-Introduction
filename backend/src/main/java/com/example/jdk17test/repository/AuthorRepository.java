package com.example.jdk17test.repository;

import com.example.jdk17test.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query(value = "select a from Author a inner join a.books b  where b.bookId =:bookId" )
    Set<Author> getAuthorsByBookId(@Param("bookId") Long bookId);
}
