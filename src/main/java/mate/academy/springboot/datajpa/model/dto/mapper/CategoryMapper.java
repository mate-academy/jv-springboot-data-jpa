package mate.academy.springboot.datajpa.model.dto.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestMapper<CategoryRequestDto, Category>,
        ResponseMapper<CategoryResponseDto, Category> {
    @Override
    public Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
