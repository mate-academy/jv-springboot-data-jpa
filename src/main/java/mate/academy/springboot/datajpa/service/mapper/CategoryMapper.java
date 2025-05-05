package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setTitle(dto.getTitle());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(model.getId());
        responseDto.setTitle(model.getTitle());
        return responseDto;
    }
}
