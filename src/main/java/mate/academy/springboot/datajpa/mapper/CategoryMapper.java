package mate.academy.springboot.datajpa.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private final CategoryService categoryService;

    public CategoryMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> parseCategoriesFromIds(String categoryIds) {
        return Arrays.stream(categoryIds.split("-"))
                .map(categoryId -> categoryService.get(Long.parseLong(categoryId)))
                .collect(Collectors.toList());
    }

    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category toModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public Category toModel(Long id, CategoryRequestDto dto) {
        Category category = new Category();
        category.setId(id);
        category.setName(dto.getName());
        return category;
    }
}
