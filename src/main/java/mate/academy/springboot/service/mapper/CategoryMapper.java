package mate.academy.springboot.service.mapper;

import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.model.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category dtoToModel(CategoryRequestDto dto) {
        return new Category(dto.getName());
    }

    public CategoryResponseDto modelToDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        return responseDto;
    }
}