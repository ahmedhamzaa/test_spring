package com.vermeg.bookstore.book_store_vermeg.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Integer managedBy;

}
