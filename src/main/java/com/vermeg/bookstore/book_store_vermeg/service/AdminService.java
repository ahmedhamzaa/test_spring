package com.vermeg.bookstore.book_store_vermeg.service;

import com.vermeg.bookstore.book_store_vermeg.domain.Admin;
import com.vermeg.bookstore.book_store_vermeg.model.AdminDTO;
import com.vermeg.bookstore.book_store_vermeg.repos.AdminRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(final AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<AdminDTO> findAll() {
        return adminRepository.findAll()
                .stream()
                .map(admin -> mapToDTO(admin, new AdminDTO()))
                .collect(Collectors.toList());
    }

    public AdminDTO get(final Integer id) {
        return adminRepository.findById(id)
                .map(admin -> mapToDTO(admin, new AdminDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final AdminDTO adminDTO) {
        final Admin admin = new Admin();
        mapToEntity(adminDTO, admin);
        return adminRepository.save(admin).getId();
    }

    public void update(final Integer id, final AdminDTO adminDTO) {
        final Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(adminDTO, admin);
        adminRepository.save(admin);
    }

    public void delete(final Integer id) {
        adminRepository.deleteById(id);
    }

    private AdminDTO mapToDTO(final Admin admin, final AdminDTO adminDTO) {
        adminDTO.setId(admin.getId());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setFullName(admin.getFullName());
        adminDTO.setPassword(admin.getPassword());
        return adminDTO;
    }

    private Admin mapToEntity(final AdminDTO adminDTO, final Admin admin) {
        admin.setEmail(adminDTO.getEmail());
        admin.setFullName(adminDTO.getFullName());
        admin.setPassword(adminDTO.getPassword());
        return admin;
    }

}
