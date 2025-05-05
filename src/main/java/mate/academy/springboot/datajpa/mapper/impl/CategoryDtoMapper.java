package mate.academy.springboot.datajpa.mapper.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDtoMapper
        implements DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public Category toModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
