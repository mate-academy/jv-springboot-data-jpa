package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.mapper.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.mapper.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;

public class CategoryMapper implements DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;
    }

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
