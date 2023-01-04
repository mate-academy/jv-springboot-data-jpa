package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    @Override
    public Category mapToModel(CategoryRequestDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }
}
