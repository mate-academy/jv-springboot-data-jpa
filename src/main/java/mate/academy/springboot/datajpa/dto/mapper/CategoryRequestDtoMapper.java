package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestDtoMapper implements RequestDtoMapper<CategoryRequestDto, Category> {
    @Override
    public Category toModel(CategoryRequestDto dto) {
        Category model = new Category();
        model.setName(dto.getName());
        return model;
    }
}
