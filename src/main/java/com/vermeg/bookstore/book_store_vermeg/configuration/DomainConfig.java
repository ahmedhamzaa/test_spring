package com.vermeg.bookstore.book_store_vermeg.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.sip.vermeg.book_store_vermeg.domain")
@EnableJpaRepositories("com.sip.vermeg.book_store_vermeg.repos")
@EnableTransactionManagement
public class DomainConfig {
}