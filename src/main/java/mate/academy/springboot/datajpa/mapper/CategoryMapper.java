package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category entity) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(categoryResponseDto.getName());
        categoryResponseDto.setId(categoryResponseDto.getId());
        return categoryResponseDto;
    }
}
