package com.vermeg.bookstore.book_store_vermeg.repos;

import com.vermeg.bookstore.book_store_vermeg.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
