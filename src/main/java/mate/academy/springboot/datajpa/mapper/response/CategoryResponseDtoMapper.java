package mate.academy.springboot.datajpa.mapper.response;

import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.lib.Mapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;

@Mapper
public class CategoryResponseDtoMapper
        implements ResponseDtoMapper<Category, CategoryResponseDto> {
    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
