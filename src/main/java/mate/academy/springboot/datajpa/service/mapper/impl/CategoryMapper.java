package mate.academy.springboot.datajpa.service.mapper.impl;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        DtoMapper<CategoryResponseDto, CategoryRequestDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        return responseDto;
    }
}
