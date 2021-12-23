package com.vermeg.bookstore.book_store_vermeg.service;

import com.vermeg.bookstore.book_store_vermeg.domain.Admin;
import com.vermeg.bookstore.book_store_vermeg.domain.Book;
import com.vermeg.bookstore.book_store_vermeg.domain.Category;
import com.vermeg.bookstore.book_store_vermeg.domain.Orders;
import com.vermeg.bookstore.book_store_vermeg.model.BookDTO;
import com.vermeg.bookstore.book_store_vermeg.repos.AdminRepository;
import com.vermeg.bookstore.book_store_vermeg.repos.BookRepository;
import com.vermeg.bookstore.book_store_vermeg.repos.CategoryRepository;
import com.vermeg.bookstore.book_store_vermeg.repos.OrdersRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final OrdersRepository ordersRepository;
    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;

    public BookService(final BookRepository bookRepository, final OrdersRepository ordersRepository,
            final AdminRepository adminRepository, final CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.ordersRepository = ordersRepository;
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapToDTO(book, new BookDTO()))
                .collect(Collectors.toList());
    }

    public BookDTO get(final Integer id) {
        return bookRepository.findById(id)
                .map(book -> mapToDTO(book, new BookDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final BookDTO bookDTO) {
        final Book book = new Book();
        mapToEntity(bookDTO, book);
        return bookRepository.save(book).getNbPage();
    }

    public void update(final Integer id, final BookDTO bookDTO) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(bookDTO, book);
        bookRepository.save(book);
    }

    public void delete(final Integer id) {
        bookRepository.deleteById(id);
    }

    private BookDTO mapToDTO(final Book book, final BookDTO bookDTO) {
        bookDTO.setId(book.getId());
        bookDTO.setRate(book.getRate());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setNbPage(book.getNbPage());
        bookDTO.setQuantity(book.getQuantity());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setImage(book.getImage());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setOrderByUsers(book.getOrderByUserOrderss() == null ? null : book.getOrderByUserOrderss().stream()
                .map(orders -> orders.getId())
                .collect(Collectors.toList()));
        bookDTO.setManagedBy(book.getManagedBy() == null ? null : book.getManagedBy().getId());
        bookDTO.setCategory(book.getCategory() == null ? null : book.getCategory().getId());
        return bookDTO;
    }

    private Book mapToEntity(final BookDTO bookDTO, final Book book) {
        book.setRate(bookDTO.getRate());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setNbPage(bookDTO.getNbPage());
        book.setQuantity(bookDTO.getQuantity());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        book.setImage(bookDTO.getImage());
        book.setPublisher(bookDTO.getPublisher());
        if (bookDTO.getOrderByUsers() != null) {
            final List<Orders> orderByUsers = ordersRepository.findAllById(bookDTO.getOrderByUsers());
            if (orderByUsers.size() != bookDTO.getOrderByUsers().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of orderByUsers not found");
            }
            book.setOrderByUserOrderss(orderByUsers.stream().collect(Collectors.toSet()));
        }
        if (bookDTO.getManagedBy() != null && (book.getManagedBy() == null || !book.getManagedBy().getId().equals(bookDTO.getManagedBy()))) {
            final Admin managedBy = adminRepository.findById(bookDTO.getManagedBy())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "managedBy not found"));
            book.setManagedBy(managedBy);
        }
        if (bookDTO.getCategory() != null && (book.getCategory() == null || !book.getCategory().getId().equals(bookDTO.getCategory()))) {
            final Category category = categoryRepository.findById(bookDTO.getCategory())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
            book.setCategory(category);
        }
        return book;
    }

}
