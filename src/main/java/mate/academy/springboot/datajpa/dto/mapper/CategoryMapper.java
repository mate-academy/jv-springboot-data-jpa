package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoRequestMapper<CategoryRequestDto, Category>,
        DtoResponseMapper<CategoryResponseDto, Category> {
    @Override
    public Category fromDto(CategoryRequestDto object) {
        Category category = new Category();
        category.setName(object.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category object) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(object.getId());
        responseDto.setName(object.getName());
        return responseDto;
    }
}
