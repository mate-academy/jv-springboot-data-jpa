package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoMapper<Category,
        CategoryRequestDto,
        CategoryResponseDto> {
    @Override
    public Category mapToModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category model) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(model.getId());
        categoryResponseDto.setName(model.getName());
        return categoryResponseDto;
    }
}
