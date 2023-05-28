package com.example.springjetbrains.repository;

import com.example.springjetbrains.entity.BookShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookShopRepository extends JpaRepository<BookShop,Integer> {
}
