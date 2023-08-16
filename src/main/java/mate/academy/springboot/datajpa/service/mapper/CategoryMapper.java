package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.config.MapperConfig;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper extends DtoMapper<
        CategoryResponseDto, Category, CategoryRequestDto> {
    CategoryResponseDto toDto(Category category);

    Category toEntity(CategoryRequestDto categoryRequestDto);
}
