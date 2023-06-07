package com.example.jdk17test.repository;

import com.example.jdk17test.entity.BookShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BookShopRepository extends JpaRepository<BookShop,Long> {
    Set<BookShop> findBookShopByShopName(String shopName);
}
