package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mappers.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public void create(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.add(categoryMapper.toModel(categoryRequestDto));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category != null) {
            categoryService.delete(category);
        }
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(
            @RequestBody CategoryRequestDto categoryRequestDto,
            @PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            throw new RuntimeException("can't update Category with id: " + id);
        }
        Category updatedCategory = categoryMapper.toModel(categoryRequestDto);
        updatedCategory.setId(id);
        categoryService.update(category);
        return categoryMapper.toDto(updatedCategory);
    }
}
