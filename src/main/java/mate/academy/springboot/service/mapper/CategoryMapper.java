package mate.academy.springboot.service.mapper;

import mate.academy.springboot.dto.request.CategoryUpdateRequestDto;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.dto.request.CategoryRequestDto;
import mate.academy.springboot.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<Category, CategoryRequestDto>,
        ResponseDtoMapper<Category, CategoryResponseDto>,
        UpdateRequestDtoMapper<Category, CategoryUpdateRequestDto> {
    @Override
    public Category toModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    @Override
    public Category toModelUpdate(CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = new Category();
        category.setId(categoryUpdateRequestDto.getId());
        category.setName(categoryUpdateRequestDto.getName());
        return category;
    }
}
