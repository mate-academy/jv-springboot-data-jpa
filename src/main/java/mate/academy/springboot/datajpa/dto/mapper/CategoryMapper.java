package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    public Category toModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setName(category.getName());
        return dto;
    }
}
