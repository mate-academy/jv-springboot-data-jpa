package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseDtoMapper implements ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
