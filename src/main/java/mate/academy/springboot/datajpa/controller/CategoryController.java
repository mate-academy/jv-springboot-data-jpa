package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;
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
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryController(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.toResponseDto(categoryMapper.toModel(requestDto));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.toResponseDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@RequestBody CategoryRequestDto requestDto,
                                      @PathVariable Long id) {
        Category category = categoryMapper.toModel(requestDto);
        category.setId(id);
        return categoryMapper.toResponseDto(categoryService.save(category));

    }
}
