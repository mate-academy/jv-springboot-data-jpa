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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CategoryControllerTest {
    private final static String FRUIT = "fruit";
    private final static Long ID = 1L;
    private CategoryController categoryController;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private Category category;
    private Category categoryWithoutId;
    private ResponseCategoryDto expected;
    private RequestCategoryDto requestCategoryDto;
    private Long id;


    @BeforeEach
    void setUp() {
        categoryService = Mockito.mock(CategoryService.class);
        categoryMapper = Mockito.mock(CategoryMapper.class);
        categoryController = new CategoryController(categoryService, categoryMapper);

        category = new Category();
        category.setId(ID);
        category.setTitle(FRUIT);

        categoryWithoutId = new Category();
        categoryWithoutId.setTitle(FRUIT);

        expected = new ResponseCategoryDto();
        expected.setId(ID);
        expected.setTitle(FRUIT);

        requestCategoryDto = new RequestCategoryDto();
        requestCategoryDto.setTitle(FRUIT);
    }

    @Test
    void create_Ok() {
        Mockito.when(categoryMapper.toModel(requestCategoryDto)).thenReturn(categoryWithoutId);
        Mockito.when(categoryService.create(categoryWithoutId)).thenReturn(category);
        Mockito.when(categoryMapper.toDto(category)).thenReturn(expected);

        ResponseCategoryDto actual = categoryController.create(requestCategoryDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getById_Ok() {
        Mockito.when(categoryService.findById(ID)).thenReturn(category);
        Mockito.when(categoryMapper.toDto(category)).thenReturn(expected);

        ResponseCategoryDto actual = categoryController.getById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_Ok() {
        Mockito.when(categoryMapper.toModel(requestCategoryDto)).thenReturn(categoryWithoutId);
        Mockito.when(categoryService.update(category)).thenReturn(category);
        Mockito.when(categoryMapper.toDto(category)).thenReturn(expected);

        ResponseCategoryDto actual = categoryController.update(ID, requestCategoryDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}