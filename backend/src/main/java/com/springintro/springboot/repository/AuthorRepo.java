package com.springintro.springboot.repository;

import com.springintro.springboot.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<AuthorModel, Long> {
}
