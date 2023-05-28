package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseDtoMapper
        implements ResponseDtoMapper<Category, CategoryResponseDto> {
    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
