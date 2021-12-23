package com.vermeg.bookstore.book_store_vermeg.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "\"admin\"")
@Getter
@Setter
public class Admin {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "managedBy")
    private Set<Book> managedByBooks;

    @OneToMany(mappedBy = "managedBy")
    private Set<Category> managedByCategorys;

}
