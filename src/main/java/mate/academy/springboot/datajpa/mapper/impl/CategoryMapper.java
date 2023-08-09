package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper
        implements DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public Category mapToModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
