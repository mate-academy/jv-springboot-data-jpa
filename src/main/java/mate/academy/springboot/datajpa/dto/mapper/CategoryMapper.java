package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        GenericMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public Category mapToModel(CategoryRequestDto categoryRequestDto) {
        return new Category()
                .setName(categoryRequestDto.getName());
    }

    @Override
    public CategoryResponseDto mapToDto(Category category) {
        return new CategoryResponseDto()
                .setId(category.getId())
                .setName(category.getName());
    }
}
