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
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private long price;
    private int yearOfPublish;
    private String genre;
    private String publisher;
    @ManyToMany
    private List<Author> authors;
    @OneToMany
    private List<Stock> stocks;
}
