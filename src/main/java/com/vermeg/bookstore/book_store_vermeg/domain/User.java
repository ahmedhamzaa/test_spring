package com.vermeg.bookstore.book_store_vermeg.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column
    private Integer phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "madeBy")
    private Set<Orders> madeByOrderss;

}
