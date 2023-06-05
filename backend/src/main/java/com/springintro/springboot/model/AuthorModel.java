package com.springintro.springboot.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="author")
@ToString
public class AuthorModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @OneToMany(targetEntity = BookModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_book", referencedColumnName = "id")
    private List<BookModel> books;

}
