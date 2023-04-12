package mate.academy.springboot.datajpa.maper.impl;

import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;
import mate.academy.springboot.datajpa.maper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(model.getId());
        categoryResponseDto.setName(model.getName());
        return categoryResponseDto;
    }
}
