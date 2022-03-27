package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.CategoryDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService, CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryDtoMapper.toDto(categoryService.save(
                categoryDtoMapper.toModel(categoryRequestDto)));
    }

    @PutMapping(value = "/{id}")
    public CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryDtoMapper.toModel(categoryRequestDto);
        category.setId(id);
        return categoryDtoMapper.toDto(categoryService.save(category));
    }

    @GetMapping(value = "/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryDtoMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable Long id) {
        categoryService.deleteDyId(id);
        return true;
    }
}
