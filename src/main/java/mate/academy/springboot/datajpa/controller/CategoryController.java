package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
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
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        Category categoryById = categoryService.get(id);
        return categoryMapper.toDto(categoryById);
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category savedCategory = categoryService
                .save(categoryMapper.toModel(categoryRequestDto));
        return categoryMapper.toDto(savedCategory);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category updateCategory = categoryService
                .update(categoryMapper.toModel(categoryRequestDto));
        updateCategory.setId(id);
        return categoryMapper.toDto(updateCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
