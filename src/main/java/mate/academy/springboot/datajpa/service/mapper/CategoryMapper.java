package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {

    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toResponseDto(Category entity) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        return responseDto;
    }
}
