package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper implements
        DtoMapper<CategoryResponseDto, Category, CategoryRequestDto> {
    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setName(model.getName());
        responseDto.setId(model.getId());
        return responseDto;
    }

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
