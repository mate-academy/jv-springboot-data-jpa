package service.mapper;

import dto.request.CategoryRequestDto;
import dto.response.CategoryResponseDto;
import model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getCategoryName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setCategoryId(category.getId());
        responseDto.setCategoryName(category.getName());
        return responseDto;
    }
}
