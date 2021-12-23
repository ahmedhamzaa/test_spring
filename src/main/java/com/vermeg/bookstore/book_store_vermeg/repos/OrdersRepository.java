package com.vermeg.bookstore.book_store_vermeg.repos;

import com.vermeg.bookstore.book_store_vermeg.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
