package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return categoryMapper.mapToDto(category);
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToModel(categoryRequestDto);
        Category savedCategory = categoryService.save(category);
        return categoryMapper.mapToDto(savedCategory);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
        @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return categoryMapper.mapToDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}