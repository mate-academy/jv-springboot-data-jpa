package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.impl.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.fromDto(categoryRequestDto);
        return categoryMapper.toDto(categoryService.save(category));
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto findCategoryById(@PathVariable Long categoryId) {
        return categoryMapper.toDto(categoryService.getById(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponseDto updateCategory(@PathVariable Long categoryId,
                                              @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.fromDto(categoryRequestDto);
        category.setId(categoryId);
        return categoryMapper.toDto(categoryService.update(category));
    }
}
