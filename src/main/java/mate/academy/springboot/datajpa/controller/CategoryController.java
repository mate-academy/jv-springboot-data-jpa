package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.request.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.request.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public Category create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return categoryService.save(category);
    }

    @GetMapping
    public CategoryResponseDto findById(@RequestParam Long id) {
        return categoryDtoMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateById(@PathVariable Long id,
                           @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryDtoMapper.mapToModel(categoryRequestDto);
        return categoryDtoMapper.mapToDto(
                categoryService.update(categoryDtoMapper.mapToModel(categoryRequestDto)));
    }
}
