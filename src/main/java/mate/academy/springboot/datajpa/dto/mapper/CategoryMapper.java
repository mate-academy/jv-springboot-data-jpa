package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toModel(CategoryRequestDto req) {
        Category category = new Category();
        category.setName(req.getName());
        return category;
    }

    public CategoryResponseDto toCategoryResponseDto(Category category) {
        CategoryResponseDto responseCategory = new CategoryResponseDto();
        responseCategory.setId(category.getId());
        responseCategory.setName(category.getName());
        return responseCategory;
    }
}
