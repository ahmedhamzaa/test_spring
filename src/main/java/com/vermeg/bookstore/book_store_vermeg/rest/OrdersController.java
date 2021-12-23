package com.vermeg.bookstore.book_store_vermeg.rest;

import com.vermeg.bookstore.book_store_vermeg.model.OrdersDTO;
import com.vermeg.bookstore.book_store_vermeg.service.OrdersService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


//@RestController
@Controller
@RequestMapping(value = "/api/orderss", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(final OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrderss() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    // for thymeleaf
    @GetMapping("/all")
    public String sendAllOrderss(Model model) {
        List<OrdersDTO> ord = (List<OrdersDTO>) ordersService.findAll();
        if (ord.size() == 0)
            ord = null;

        model.addAttribute("orders", ord);
        return "user/navs";
        //  return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrders(@PathVariable final Integer id) {
        return ResponseEntity.ok(ordersService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createOrders(@RequestBody @Valid final OrdersDTO ordersDTO) {
        return new ResponseEntity<>(ordersService.create(ordersDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrders(@PathVariable final Integer id,
            @RequestBody @Valid final OrdersDTO ordersDTO) {
        ordersService.update(id, ordersDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable final Integer id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
