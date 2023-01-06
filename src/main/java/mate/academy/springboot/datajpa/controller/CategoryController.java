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

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.mapToDto(categoryService.create(
                categoryMapper.mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    CategoryResponseDto update(@RequestBody CategoryRequestDto requestDto, @PathVariable Long id) {
        Category category = categoryService.getById(id);
        category.setName(requestDto.getName());
        return categoryMapper.mapToDto(categoryService.update(category));
    }
}
