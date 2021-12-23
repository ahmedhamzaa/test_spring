package com.vermeg.bookstore.book_store_vermeg.repos;

import com.vermeg.bookstore.book_store_vermeg.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
