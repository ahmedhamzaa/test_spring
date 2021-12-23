package com.vermeg.bookstore.book_store_vermeg.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrdersDTO {

    private Integer id;

    private LocalDate createdDate;

    private LocalDate shippedDate;

    @Size(max = 255)
    private String status;

    private Double totalPrice;

    @NotNull
    private Integer madeBy;

}
