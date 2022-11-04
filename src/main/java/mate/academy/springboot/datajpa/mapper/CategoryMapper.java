package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    public Category toModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setId(categoryRequestDto.getId());
        category.setName(categoryRequestDto.getName());
        return category;
    }
}
