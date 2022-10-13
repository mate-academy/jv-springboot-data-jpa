package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
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
        Category category = categoryService.get(id);
        return categoryMapper.toDto(category);
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryMapper.toModel(requestDto));
        return categoryMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(
            @PathVariable Long id, @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        categoryService.update(id,category);
        return categoryMapper.toDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
