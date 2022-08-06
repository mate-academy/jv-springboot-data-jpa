package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper
                .toResponseDto(
                        categoryService
                                .save(
                                        categoryMapper
                                                .toModel(requestDto)));
    }

    @GetMapping
    public CategoryResponseDto findById(@RequestParam Long id) {
        return categoryMapper.toResponseDto(categoryService.findBy(id));
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestBody CategoryResponseDto category) {
        categoryService.update(category.getName(), category.getId());
    }
}
