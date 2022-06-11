package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.ResponseCategoryDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {
    private CategoryController categoryController;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;


    @BeforeEach
    void setUp() {
        categoryService = Mockito.mock(CategoryService.class);
        categoryMapper = new CategoryMapper();
        categoryController = new CategoryController(categoryService, categoryMapper);
    }

    @Test
    void create_OK() {
        ResponseCategoryDto expected = new ResponseCategoryDto();
        expected.setId(1L);
        expected.setTitle("fruit");

        RequestCategoryDto requestCategoryDto = new RequestCategoryDto();
        requestCategoryDto.setTitle("fruit");

        Category category = new Category();
        category.setId(1L);
        category.setTitle("fruit");

        Mockito.when(categoryService.create(new Category())).thenReturn(category);

        ResponseCategoryDto actual = categoryController.create(requestCategoryDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}