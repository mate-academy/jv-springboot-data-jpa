package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toResponseDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    public Category toModel(CategoryRequestDto requestDto) {
        return Category.builder().name(requestDto.getName()).build();
    }
}
