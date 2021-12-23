package com.vermeg.bookstore.book_store_vermeg.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String fullName;

    private Integer phone;

    @NotNull
    @Size(max = 255)
    private String password;

}
