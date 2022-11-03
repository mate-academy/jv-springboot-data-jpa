package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.category.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDto toResponseDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;
    }

    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
