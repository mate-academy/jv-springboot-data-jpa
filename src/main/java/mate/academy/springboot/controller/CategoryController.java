package mate.academy.springboot.controller;

import mate.academy.springboot.dto.CategoryRequestDto;
import mate.academy.springboot.dto.CategoryResponseDto;
import mate.academy.springboot.mapper.CategoryMapper;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.service.CategoryService;
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
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryMapper.mapToDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.mapToDto(categoryService
                .add(categoryMapper.mapToModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return categoryMapper.mapToDto(categoryService.update(category));
    }
}
