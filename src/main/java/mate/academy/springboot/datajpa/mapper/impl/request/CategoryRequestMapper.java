package mate.academy.springboot.datajpa.mapper.impl.request;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper implements DtoRequestMapper<CategoryRequestDto, Category> {
    @Override
    public Category fromDto(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
