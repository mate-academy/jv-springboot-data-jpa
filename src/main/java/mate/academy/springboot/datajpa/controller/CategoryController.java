package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.impl.CategoryDtoMapper;
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
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto requestDto) {
        Category savedCategory = categoryService.save(categoryDtoMapper.mapToModel(requestDto));
        return categoryDtoMapper.mapToDto(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryDtoMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryDtoMapper.mapToModel(requestDto);
        categoryService.save(category);
        return categoryDtoMapper.mapToDto(category);
    }

}
