package com.ericpinto.catalogms.service;

import com.ericpinto.catalogms.exception.ObjectInvalidException;
import com.ericpinto.catalogms.exception.ObjectNotFoundException;
import com.ericpinto.catalogms.exception.UniqueViolationException;
import com.ericpinto.catalogms.model.CategoryModel;
import com.ericpinto.catalogms.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.ericpinto.catalogms.stub.CategoryStub.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private static final String ID = "66aacbf0a028f53230949c56";

    @Test
    void shouldThrowExceptionWhenCategoryAlreadyExists() {
        when(categoryRepository.existsByName(createCategoryStub().getName())).thenReturn(true);

        Exception exception = assertThrows(UniqueViolationException.class, () -> {
            categoryService.create(createCategoryDTOStub());
        });

        String expectedMessage = "Category with name " + createCategoryDTOStub().name() + " already exists";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameIsEmpty() {
        Exception exception = assertThrows(ObjectInvalidException.class, () -> {
            categoryService.create(teste());
        });

        String expectedMessage = "Category name cannot be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldCreateCategory() {
        categoryService.create(createCategoryDTOStub());
        verify(categoryRepository, times(1)).save(any(CategoryModel.class));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound(){
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            categoryService.getOne(ID);
        });

        String expectedMessage = "Category with id " + ID + " not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldFindACategory(){
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(createCategoryStub()));

        CategoryModel category = categoryService.getOne(ID);

        assertNotNull(category);
        assertEquals(createCategoryStub().getId(), category.getId());
        assertEquals(createCategoryStub().getName(), category.getName());
    }

    @Test
    void shouldGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(createCategoryStub()));

        List<CategoryModel> categories = categoryService.getAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(createCategoryStub().getId(), categories.get(0).getId());
        assertEquals(createCategoryStub().getName(), categories.get(0).getName());
    }

    @Test
    void shouldUpdateCategory(){
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(createCategoryStub()));

        categoryService.updateName(ID, "Periféricos");

        verify(categoryRepository, times(1)).save(any(CategoryModel.class));
    }

    @Test
    void shouldDeleteCategory(){
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(createCategoryStub()));

        categoryService.delete(ID);

        verify(categoryRepository, times(1)).deleteById(any(String.class));
    }


}