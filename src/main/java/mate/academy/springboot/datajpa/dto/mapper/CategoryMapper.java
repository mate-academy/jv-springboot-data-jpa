package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        ModelMapper<CategoryResponseDto, Category, CategoryRequestDto> {
    @Override
    public CategoryResponseDto toResponseDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category model = new Category();
        model.setName(requestDto.getName());
        return model;
    }
}
