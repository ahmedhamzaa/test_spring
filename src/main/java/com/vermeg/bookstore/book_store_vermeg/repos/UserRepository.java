package com.vermeg.bookstore.book_store_vermeg.repos;

import com.vermeg.bookstore.book_store_vermeg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
