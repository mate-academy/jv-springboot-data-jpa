package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoRequestMapper<CategoryRequestDto, Category>,
        DtoResponseMapper<CategoryResponseDto, Category> {
    @Override
    public Category toModel(CategoryRequestDto dto) {
        Category model = new Category();
        model.setName(dto.getName());
        return model;
    }

    @Override
    public CategoryResponseDto toDto(Category dto) {
        CategoryResponseDto model = new CategoryResponseDto();
        model.setId(dto.getId());
        model.setName(dto.getName());
        return model;
    }
}
