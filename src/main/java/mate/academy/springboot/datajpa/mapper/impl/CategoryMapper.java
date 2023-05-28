package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;

public class CategoryMapper implements
        RequestDtoMapper<Category, CategoryRequestDto>,
        ResponseDtoMapper<Category, CategoryResponseDto> {
    @Override
    public Category toModel(CategoryRequestDto dto) {
        return new Category(dto.getName());
    }

    @Override
    public CategoryResponseDto toDto(Category model) {
        return new CategoryResponseDto(model.getId(), model.getName());
    }
}
