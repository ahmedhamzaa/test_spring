package com.vermeg.bookstore.book_store_vermeg.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Book {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer rate;

    @Column(nullable = false)
    private String name;

    @Column
    private String author;

    @Column
    private Integer nbPage;

    @Column
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(name = "\"description\"")
    private String description;

    @Column
    private String image;

    @Column
    private String publisher;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id")
    )
    private Set<Orders> orderByUserOrderss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managed_by_id", nullable = false)
    private Admin managedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
