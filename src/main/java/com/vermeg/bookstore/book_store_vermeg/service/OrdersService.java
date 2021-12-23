package com.vermeg.bookstore.book_store_vermeg.service;

import com.vermeg.bookstore.book_store_vermeg.domain.Orders;
import com.vermeg.bookstore.book_store_vermeg.domain.User;
import com.vermeg.bookstore.book_store_vermeg.model.OrdersDTO;
import com.vermeg.bookstore.book_store_vermeg.repos.OrdersRepository;
import com.vermeg.bookstore.book_store_vermeg.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;

    public OrdersService(final OrdersRepository ordersRepository,
            final UserRepository userRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
    }

    public List<OrdersDTO> findAll() {
        return ordersRepository.findAll()
                .stream()
                .map(orders -> mapToDTO(orders, new OrdersDTO()))
                .collect(Collectors.toList());
    }

    public OrdersDTO get(final Integer id) {
        return ordersRepository.findById(id)
                .map(orders -> mapToDTO(orders, new OrdersDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final OrdersDTO ordersDTO) {
        final Orders orders = new Orders();
        mapToEntity(ordersDTO, orders);
        return ordersRepository.save(orders).getId();
    }

    public void update(final Integer id, final OrdersDTO ordersDTO) {
        final Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ordersDTO, orders);
        ordersRepository.save(orders);
    }

    public void delete(final Integer id) {
        ordersRepository.deleteById(id);
    }

    private OrdersDTO mapToDTO(final Orders orders, final OrdersDTO ordersDTO) {
        ordersDTO.setId(orders.getId());
        ordersDTO.setCreatedDate(orders.getCreatedDate());
        ordersDTO.setShippedDate(orders.getShippedDate());
        ordersDTO.setStatus(orders.getStatus());
        ordersDTO.setTotalPrice(orders.getTotalPrice());
        ordersDTO.setMadeBy(orders.getMadeBy() == null ? null : orders.getMadeBy().getId());
        return ordersDTO;
    }

    private Orders mapToEntity(final OrdersDTO ordersDTO, final Orders orders) {
        orders.setCreatedDate(ordersDTO.getCreatedDate());
        orders.setShippedDate(ordersDTO.getShippedDate());
        orders.setStatus(ordersDTO.getStatus());
        orders.setTotalPrice(ordersDTO.getTotalPrice());
        if (ordersDTO.getMadeBy() != null && (orders.getMadeBy() == null || !orders.getMadeBy().getId().equals(ordersDTO.getMadeBy()))) {
            final User madeBy = userRepository.findById(ordersDTO.getMadeBy())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "madeBy not found"));
            orders.setMadeBy(madeBy);
        }
        return orders;
    }

}
