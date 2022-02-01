package mate.academy.springboot.datajpa.dto.response.mapper.impl;

import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.response.mapper.ResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper implements ResponseMapper<CategoryResponseDto, Category> {
    @Override
    public CategoryResponseDto mapToDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        return responseDto;
    }
}
