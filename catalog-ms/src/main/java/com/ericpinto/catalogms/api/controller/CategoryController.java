package com.ericpinto.catalogms.api.controller;

import com.ericpinto.catalogms.api.dto.CreateCategoryDTO;
import com.ericpinto.catalogms.model.CategoryModel;
import com.ericpinto.catalogms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryModel> create(@RequestBody CreateCategoryDTO createCategoryDTO)  {
        return ResponseEntity.ok(categoryService.create(createCategoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryService.getOne(id));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateName(@PathVariable("id") String id, @RequestParam String name) {
        categoryService.updateName(id, name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
