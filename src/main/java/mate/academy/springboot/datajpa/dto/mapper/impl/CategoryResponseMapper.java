package mate.academy.springboot.datajpa.dto.mapper.impl;

import mate.academy.springboot.datajpa.dto.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper implements DtoResponseMapper<CategoryResponseDto, Category> {
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }
}
