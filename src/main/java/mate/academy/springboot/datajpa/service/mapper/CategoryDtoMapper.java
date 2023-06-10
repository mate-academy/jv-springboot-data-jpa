package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public abstract class CategoryDtoMapper implements DtoMapper<Category,
        CategoryResponseDto,
        CategoryRequestDto> {

    @Override
    public abstract CategoryResponseDto toDto(Category entity);

    @Override
    public abstract Category toModel(CategoryRequestDto dto);
}
