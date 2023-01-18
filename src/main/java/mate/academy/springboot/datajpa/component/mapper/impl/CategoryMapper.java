package mate.academy.springboot.datajpa.component.mapper.impl;

import mate.academy.springboot.datajpa.component.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.component.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
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
    public CategoryResponseDto mapToDto(Category model) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
