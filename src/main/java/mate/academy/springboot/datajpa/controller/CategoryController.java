package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.Impl.CategoryDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @GetMapping("/inject")
    public void saveCategory() {
        Category category = new Category();
        category.setName("Phones");
        categoryService.save(category);
    }

    @PostMapping
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        return categoryDtoMapper.toDto(categoryService.save(categoryDtoMapper.toModel(requestDto)));
    }
}
