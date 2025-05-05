package mate.academy.springboot.datajpa.mapper.impl.response;

import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper implements DtoResponseMapper<CategoryResponseDto, Category> {

    @Override
    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
