package mate.academy.springboot.datajpa.service.mapper.response;

import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper implements ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;
    }
}
