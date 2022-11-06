package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestDtoMapper implements RequestDtoMapper<CategoryRequestDto, Category> {
    @Override
    public Category toModel(CategoryRequestDto data) {
        Category category = new Category();
        category.setName(data.getName());
        return category;
    }
}
