package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.req.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.resp.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements ResponseDtoMapper<CategoryResponseDto, Category>,
        RequestDtoMapper<CategoryRequestDto, Category> {
    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
