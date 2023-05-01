package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto dto) {
        Category category = categoryMapper.toCategoryModel(dto);
        Category savedCategory = categoryService.create(category);
        return categoryMapper.toCategoryResponseDto(savedCategory);
    }

    @GetMapping("{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return categoryMapper.toCategoryResponseDto(category);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto dto) {
        Category category = categoryMapper.toCategoryModel(dto);
        category.setId(id);
        return categoryMapper.toCategoryResponseDto(categoryService.update(category));
    }
}
