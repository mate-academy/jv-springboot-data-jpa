package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryMapperTest {
    private static final Long ID = 1L;
    private static final String TITLE = "fruit";
    private CategoryMapper categoryMapper;
    private Category category;
    private ResponseCategoryDto expectedResponseDto;
    private RequestCategoryDto requestCategoryDto;
    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapper();

        category = new Category();
        category.setId(ID);
        category.setTitle(TITLE);

        expectedResponseDto = new ResponseCategoryDto();
        expectedResponseDto.setId(ID);
        expectedResponseDto.setTitle(TITLE);

        requestCategoryDto = new RequestCategoryDto();
        requestCategoryDto.setTitle(TITLE);
    }

    @Test
    void toDtoOk() {
        ResponseCategoryDto actualResponseDto = categoryMapper.toDto(category);

        Assertions.assertNotNull(actualResponseDto);
        Assertions.assertEquals(expectedResponseDto, actualResponseDto);
    }

    @Test
    void toModelOk() {
        category.setId(null);
        Category actual = categoryMapper.toModel(requestCategoryDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(category, actual);
    }
}