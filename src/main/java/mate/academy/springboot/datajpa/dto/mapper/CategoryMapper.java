package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public Category toModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(model.getId());
        categoryResponseDto.setName(model.getName());
        return categoryResponseDto;
    }
}
