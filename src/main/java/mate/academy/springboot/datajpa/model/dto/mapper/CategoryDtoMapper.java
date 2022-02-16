package mate.academy.springboot.datajpa.model.dto.mapper;

import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper {
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;
    }
    public Category toModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }
}
