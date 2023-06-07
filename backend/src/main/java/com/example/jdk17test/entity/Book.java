package com.example.jdk17test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "shop_id",referencedColumnName = "shopId")
    private BookShop bookShop;
    private String title;
    private long price;
    private int  yearOfPublish;
    private String genre;
    private String publisher;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "book_authors",joinColumns = @JoinColumn(name = "book_id"),inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors=new HashSet<>();
}
