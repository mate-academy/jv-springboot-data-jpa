package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper implements RequestDtoMapper<CategoryRequestDto, Category> {
    public Category toModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }
}
