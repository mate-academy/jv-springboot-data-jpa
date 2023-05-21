package mate.academy.springboot.datajpa.dto.mapper.impl;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setId(category.getId());
        return categoryResponseDto;
    }
}
