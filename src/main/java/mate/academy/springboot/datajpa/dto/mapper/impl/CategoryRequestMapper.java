package mate.academy.springboot.datajpa.dto.mapper.impl;

import mate.academy.springboot.datajpa.dto.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper implements DtoRequestMapper<CategoryRequestDto, Category> {
    public Category fromDto(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
