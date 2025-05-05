package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.mapper.Mapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public Category toModel(CategoryRequestDto dto) {
        return new Category(dto.getId(), dto.getName());
    }

    @Override
    public CategoryResponseDto toDto(Category model) {
        return new CategoryResponseDto(model.getName());
    }
}
