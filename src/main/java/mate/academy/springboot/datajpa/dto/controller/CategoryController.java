package mate.academy.springboot.datajpa.dto.controller;

import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
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
        return categoryMapper.mapToDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.mapToDto(
                categoryService.add(categoryMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.mapToDto(
                categoryService.update(categoryMapper.mapToModel(requestDto).setId(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
