package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseDtoMapper {
    public CategoryResponseDto toDto(Category entity) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(entity.getId());
        categoryResponseDto.setName(entity.getName());
        return categoryResponseDto;
    }
}
