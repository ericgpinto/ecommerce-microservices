package com.ericpinto.catalogms.service;

import com.ericpinto.catalogms.api.dto.CreateCategoryDTO;
import com.ericpinto.catalogms.exception.ObjectInvalidException;
import com.ericpinto.catalogms.exception.ObjectNotFoundException;
import com.ericpinto.catalogms.exception.UniqueViolationException;
import com.ericpinto.catalogms.model.CategoryModel;
import com.ericpinto.catalogms.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryModel create(CreateCategoryDTO createCategoryDTO)  {
        log.info("Creating category: {}", createCategoryDTO);

        validateName(createCategoryDTO.name());

        CategoryModel categorySaved = categoryRepository.save(CategoryModel.create(createCategoryDTO.name()));
        log.info("Created category: {}", categorySaved);
        return categorySaved;
    }

    public CategoryModel getOne(String id) {
        log.info("Getting category: id {}", id);
        Optional<CategoryModel> category = categoryRepository.findById(id);

        if (category.isEmpty()){
            log.error("Category with id {} not found", id);
            throw new ObjectNotFoundException("Category with id " + id + " not found");
        }
        log.info("Found category: {}", category.get());
        return category.get();
    }

    public List<CategoryModel> getAll() {
        log.info("Getting all categories");
        List<CategoryModel> categories = categoryRepository.findAll();
        log.info("Found {} categories", categoryRepository.count());
        return categories;
    }

    public void updateName(String id, String name){
        log.info("Updating category: id {} name {}", id, name);
        CategoryModel category = getOne(id);

        validateName(name);

        category.changeName(name);
        categoryRepository.save(category);
        log.info("Updated category: {}", category);
    }

    public void delete(String id) {
        log.info("Deleting category: id {}", id);

        CategoryModel category = getOne(id);
        categoryRepository.deleteById(category.getId());

        log.info("Deleted category: {}", category);
    }


    private void validateName(String name) {
        if (name.isEmpty() || name.isBlank()){
            log.error("Invalid category name");
            throw new ObjectInvalidException("Category name cannot be empty");
        }

        Boolean existsCategory = categoryRepository.existsByName(name);
        if (Boolean.TRUE.equals(existsCategory)){
            log.error("Category with name {} already exists", name);
            throw new UniqueViolationException("Category with name " + name + " already exists");
        }
    }
}
