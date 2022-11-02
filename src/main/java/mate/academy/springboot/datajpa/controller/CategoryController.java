package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
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

    @PostMapping
    public ResponseCategoryDto create(@RequestBody RequestCategoryDto categoryDto) {
        Category category = categoryService.save(
                categoryMapper.mapToModel(categoryDto));
        return categoryMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getByID(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return categoryMapper.mapToDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping
    public ResponseCategoryDto update(@PathVariable Long id,
                                      @RequestBody RequestCategoryDto requestCategoryDto) {
        Category category = categoryMapper.mapToModel(requestCategoryDto);
        category.setId(id);
        return categoryMapper.mapToDto(category);
    }
}
