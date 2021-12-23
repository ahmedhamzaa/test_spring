package com.vermeg.bookstore.book_store_vermeg.domain;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Orders {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDate createdDate;

    @Column
    private LocalDate shippedDate;

    @Column
    private String status;

    @Column
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "made_by_id", nullable = false)
    private User madeBy;

    @ManyToMany(mappedBy = "orderByUserOrderss")
    private Set<Book> orderByUserBooks;

}
