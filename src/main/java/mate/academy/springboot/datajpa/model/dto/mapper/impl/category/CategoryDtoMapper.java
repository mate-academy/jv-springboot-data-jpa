package mate.academy.springboot.datajpa.model.dto.mapper.impl.category;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.dto.request.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.category.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto dto, Long id) {
        return Category.builder()
                .id(id)
                .name(dto.getName())
                .build();
    }

    @Override
    public CategoryResponseDto mapToDto(Category model) {
        return CategoryResponseDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}
