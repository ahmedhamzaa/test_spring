package com.vermeg.bookstore.book_store_vermeg.service;

import com.vermeg.bookstore.book_store_vermeg.domain.Admin;
import com.vermeg.bookstore.book_store_vermeg.domain.Category;
import com.vermeg.bookstore.book_store_vermeg.model.CategoryDTO;
import com.vermeg.bookstore.book_store_vermeg.repos.AdminRepository;
import com.vermeg.bookstore.book_store_vermeg.repos.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AdminRepository adminRepository;

    public CategoryService(final CategoryRepository categoryRepository,
            final AdminRepository adminRepository) {
        this.categoryRepository = categoryRepository;
        this.adminRepository = adminRepository;
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .collect(Collectors.toList());
    }

    public CategoryDTO get(final Integer id) {
        return categoryRepository.findById(id)
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final CategoryDTO categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category);
        return categoryRepository.save(category).getId();
    }

    public void update(final Integer id, final CategoryDTO categoryDTO) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(categoryDTO, category);
        categoryRepository.save(category);
    }

    public void delete(final Integer id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO mapToDTO(final Category category, final CategoryDTO categoryDTO) {
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setManagedBy(category.getManagedBy() == null ? null : category.getManagedBy().getId());
        return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category) {
        category.setName(categoryDTO.getName());
        if (categoryDTO.getManagedBy() != null && (category.getManagedBy() == null || !category.getManagedBy().getId().equals(categoryDTO.getManagedBy()))) {
            final Admin managedBy = adminRepository.findById(categoryDTO.getManagedBy())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "managedBy not found"));
            category.setManagedBy(managedBy);
        }
        return category;
    }

}
