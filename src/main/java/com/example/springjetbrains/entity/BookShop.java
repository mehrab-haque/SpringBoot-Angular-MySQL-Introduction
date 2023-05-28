package com.example.springjetbrains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_shop")
public class BookShop {
    @Id
    @GeneratedValue
    private int id;
    private int shop_number;
    private String location;
    private String contact_no;
    private String email;
    @OneToMany
    private List<Book> stocks;
}
