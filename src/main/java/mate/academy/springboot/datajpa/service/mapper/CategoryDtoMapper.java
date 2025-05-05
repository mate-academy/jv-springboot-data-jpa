package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper extends DtoMapper<Category,
        CategoryResponseDto,
        CategoryRequestDto> {
    @Override
    CategoryResponseDto toDto(Category entity);

    @Override
    Category toModel(CategoryRequestDto dto);
}
