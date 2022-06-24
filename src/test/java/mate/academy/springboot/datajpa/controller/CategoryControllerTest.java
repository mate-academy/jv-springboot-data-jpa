package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.exception.ServiceDataException;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryControllerTest {
    private final static String FRUIT = "fruit";
    private final static Long ID = 1L;
    private static final String MESSAGE = "Can't find a category by Id : " + ID + " !";
    private CategoryController categoryController;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private Category category;
    private Category categoryWithoutId;
    private ResponseCategoryDto expectedDto;
    private RequestCategoryDto requestDto;

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

        expectedDto = new ResponseCategoryDto();
        expectedDto.setId(ID);
        expectedDto.setTitle(FRUIT);

        requestDto = new RequestCategoryDto();
        requestDto.setTitle(FRUIT);
    }

    @Test
    void createOk() {
        Mockito.when(categoryMapper.toModel(requestDto)).thenReturn(categoryWithoutId);
        Mockito.when(categoryService.create(categoryWithoutId)).thenReturn(category);
        Mockito.when(categoryMapper.toDto(category)).thenReturn(expectedDto);

        ResponseCategoryDto actual = categoryController.create(requestDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedDto, actual);
    }

    @Test
    void getByIdOk() {
        Mockito.when(categoryService.findById(ID)).thenReturn(category);
        Mockito.when(categoryMapper.toDto(category)).thenReturn(expectedDto);

        ResponseCategoryDto actual = categoryController.getById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedDto, actual);
    }

    @Test
    void getByIdCatchServiceDataException() {
        Mockito.when(categoryService.findById(ID)).thenThrow(new ServiceDataException(MESSAGE));

        ServiceDataException actual = Assertions.assertThrows(ServiceDataException.class, ()
                -> categoryController.getById(ID));

        Assertions.assertEquals(MESSAGE, actual.getMessage());
    }

    @Test
    void updateOk() {
        Mockito.when(categoryMapper.toModel(requestDto)).thenReturn(categoryWithoutId);
        Mockito.when(categoryService.update(category)).thenReturn(category);
        Mockito.when(categoryMapper.toDto(category)).thenReturn(expectedDto);

        ResponseCategoryDto actual = categoryController.update(ID, requestDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedDto, actual);
    }
}