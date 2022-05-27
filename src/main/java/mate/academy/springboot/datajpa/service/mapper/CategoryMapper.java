package mate.academy.springboot.datajpa.service.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements RequestDtoMapper<Category, CategoryRequestDto>,
        ResponseDtoMapper<Category, CategoryResponseDto> {
    private final ModelMapper mapper;

    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        return mapper.map(dto, Category.class);
    }

    @Override
    public CategoryResponseDto mapToDto(Category model) {
        return mapper.map(model, CategoryResponseDto.class);
    }
}
