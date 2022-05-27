package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public CategoryResponseDto mapToDto(Category category) {
        return new CategoryResponseDto().setName(category.getName());
    }

    @Override
    public Category mapToEntity(CategoryRequestDto dto) {
        return new Category().setName(dto.getName());
    }
}
