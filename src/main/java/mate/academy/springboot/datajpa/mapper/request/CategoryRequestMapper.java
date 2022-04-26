package mate.academy.springboot.datajpa.mapper.request;

import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper implements DtoRequestMapper<CategoryRequestDto, Category> {
    private final CategoryService categoryService;

    public CategoryRequestMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return categoryService.save(category);
    }
}
