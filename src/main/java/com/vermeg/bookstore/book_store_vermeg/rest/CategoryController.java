package com.vermeg.bookstore.book_store_vermeg.rest;

import com.vermeg.bookstore.book_store_vermeg.model.CategoryDTO;
import com.vermeg.bookstore.book_store_vermeg.service.CategoryService;
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
import org.springframework.web.bind.annotation.RestController;

/*
@Controller
@RequestMapping(value = "/api/categorys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategorys() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable final Integer id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createCategory(
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.create(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable final Integer id,
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable final Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
*/
@Controller
@RequestMapping(value = "/api/categorys")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getAllCategory(Model model) {

        List<CategoryDTO> category = (List<CategoryDTO>) categoryService.findAll();
        if (category.size() == 0)
            category = null;

        model.addAttribute("category", category);
        return "category/listCategory";
    }

    @GetMapping("/list/{id}")
    public String getBook(@PathVariable final Integer id ,Model model) {

        CategoryDTO category = (CategoryDTO) categoryService.get(id);
        model.addAttribute("category", category);
        return "category/updateCategory";
    }
    @GetMapping("add")
    public String showAddArticleForm(CategoryDTO category, Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "category/addCategory";
    }
    @PostMapping("add")
    public String createBook(@Valid final CategoryDTO category) {

        categoryService.create(category);
        return "category/addCategory";
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable final Integer id,
                                               @RequestBody @Valid final CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable final Integer id ) {

        categoryService.delete(id);
        return "redirect:../list";
    }


}