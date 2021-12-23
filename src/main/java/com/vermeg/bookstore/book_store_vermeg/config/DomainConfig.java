package com.vermeg.bookstore.book_store_vermeg.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.vermeg.bookstore.book_store_vermeg.domain")
@EnableJpaRepositories("com.vermeg.bookstore.book_store_vermeg.repos")
@EnableTransactionManagement
public class DomainConfig {
}
