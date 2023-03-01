package mate.academy.springboot.datajpa.model.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    public Category toModel(CategoryResponseDto categoryResponseDto) {
        Category category = new Category();
        category.setId(categoryResponseDto.getId());
        category.setName(categoryResponseDto.getName());
        return category;
    }
}
