package mate.academy.springboot.datajpa.mapper.request;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.lib.Mapper;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.Category;

@Mapper
public class CategoryRequestDtoMapper
        implements RequestDtoMapper<Category, CategoryRequestDto> {
    @Override
    public Category toModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }
}
