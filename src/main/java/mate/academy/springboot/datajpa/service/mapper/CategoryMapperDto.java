package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponceDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperDto implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponceDtoMapper<CategoryResponceDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryResponceDto mapToDto(Category category) {
        CategoryResponceDto categoryResponceDto = new CategoryResponceDto();
        categoryResponceDto.setId(category.getId());
        categoryResponceDto.setName(category.getName());
        return categoryResponceDto;
    }
}
