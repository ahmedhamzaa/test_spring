package com.vermeg.bookstore.book_store_vermeg.rest;

import com.vermeg.bookstore.book_store_vermeg.model.AdminDTO;
import com.vermeg.bookstore.book_store_vermeg.service.AdminService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(value = "/api/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final AdminService adminService;

    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable final Integer id) {
        return ResponseEntity.ok(adminService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createAdmin(@RequestBody @Valid final AdminDTO adminDTO) {
        return new ResponseEntity<>(adminService.create(adminDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable final Integer id,
            @RequestBody @Valid final AdminDTO adminDTO) {
        adminService.update(id, adminDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable final Integer id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
