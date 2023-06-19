package mate.academy.springboot.datajpa.service.mappers;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoMapper<Category,
        CategoryResponseDto, CategoryRequestDto> {

    @Override
    public Category mapToModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setId(category.getId());
        return categoryResponseDto;
    }
}
