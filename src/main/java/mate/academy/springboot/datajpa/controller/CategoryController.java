package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.RequestCategory;
import mate.academy.springboot.datajpa.dto.response.ResponseCategory;
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
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryController(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseCategory add(@RequestBody RequestCategory requestCategory) {
        Category category = categoryMapper.fromDto(requestCategory);
        return categoryMapper.toDto(categoryService.add(category));
    }

    @GetMapping("/{id}")
    public ResponseCategory getById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseCategory update(@PathVariable Long id,
                                   @RequestBody RequestCategory responseCategory) {
        Category category = categoryMapper.fromDto(responseCategory);
        category.setId(id);
        return categoryMapper.toDto(categoryService.update(category));
    }
}
