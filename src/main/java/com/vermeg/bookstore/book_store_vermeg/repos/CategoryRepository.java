package com.vermeg.bookstore.book_store_vermeg.repos;

import com.vermeg.bookstore.book_store_vermeg.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
