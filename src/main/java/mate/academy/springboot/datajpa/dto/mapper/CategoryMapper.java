package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.requestdto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.responsedto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<Category, CategoryRequestDto>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
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
}
