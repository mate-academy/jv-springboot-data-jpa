package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
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
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<Category, CategoryResponseDto, CategoryRequestDto> categoryMapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.create(categoryMapper.mapToModel(requestDto));
        return categoryMapper.mapToDto(category);
    }

    @GetMapping("{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return categoryMapper.mapToDto(category);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                               @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryMapper.mapToModel(requestDto);
        category.setId(id);
        return categoryMapper.mapToDto(categoryService.create(category));
    }
}
