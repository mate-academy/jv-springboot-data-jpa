package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequest;
import mate.academy.springboot.datajpa.dto.CategoryResponse;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<Category, CategoryRequest, CategoryResponse> {

    @Override
    public CategoryResponse mapToDto(Category category) {
        return new CategoryResponse().setName(category.getName());
    }

    @Override
    public Category mapToEntity(CategoryRequest dto) {
        return new Category().setName(dto.getName());
    }
}
