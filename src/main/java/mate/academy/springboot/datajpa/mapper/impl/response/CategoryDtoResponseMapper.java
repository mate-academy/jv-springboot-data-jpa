package mate.academy.springboot.datajpa.mapper.impl.response;

import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoResponseMapper implements DtoResponseMapper<CategoryResponseDto, Category> {
    @Override
    public CategoryResponseDto toDto(Category object) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(object.getId());
        categoryResponseDto.setName(object.getName());
        return categoryResponseDto;
    }
}
