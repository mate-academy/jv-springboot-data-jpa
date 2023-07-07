package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper
        implements DtoMapper<Category, CategoryResponseDto, CategoryRequestDto> {
    @Override
    public CategoryResponseDto mapToDto(Category entity) {
        return new CategoryResponseDto(entity.getId(), entity.getName());
    }

    @Override
    public Category mapToEntity(CategoryRequestDto dto) {
        return new Category(dto.getName());
    }
}
