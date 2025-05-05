package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<CategoryRequestDto, CategoryResponseDto, Category> categoryMapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToEntity(categoryRequestDto);
        return categoryMapper.mapToDto(categoryService.save(category));
    }

    @GetMapping("{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PostMapping("{id}")
    public CategoryResponseDto updateById(@PathVariable Long id,
                                          @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToEntity(categoryRequestDto);
        category.setId(id);
        return categoryMapper.mapToDto(categoryService.save(category));
    }
}
