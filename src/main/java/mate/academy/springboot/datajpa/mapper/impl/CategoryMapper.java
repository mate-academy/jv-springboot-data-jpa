package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.Mapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<CategoryRequestDto, CategoryResponseDto, Category> {

    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;
    }

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
