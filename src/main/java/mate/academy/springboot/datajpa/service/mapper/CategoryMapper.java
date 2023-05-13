package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements ResponseDtoMapper<CategoryResponseDto, Category>,
        RequestDtoMapper<CategoryRequestDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
