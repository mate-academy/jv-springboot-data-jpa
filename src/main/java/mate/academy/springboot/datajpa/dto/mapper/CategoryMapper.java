package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoMapper<Category,
        CategoryResponseDto, CategoryRequestDto> {
    @Override
    public CategoryResponseDto toDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        return responseDto;
    }

    @Override
    public Category toModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
