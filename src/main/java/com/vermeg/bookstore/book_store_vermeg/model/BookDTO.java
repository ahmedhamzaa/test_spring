package com.vermeg.bookstore.book_store_vermeg.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDTO {

    private Integer id;

    private Integer rate;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String author;

    private Integer nbPage;

    private Integer quantity;

    @NotNull
    private Double price;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String image;

    @Size(max = 255)
    private String publisher;

    @NotNull
    private Integer managedBy;

    @NotNull
    private Integer category;

    private List<Integer> orderByUsers;

}
