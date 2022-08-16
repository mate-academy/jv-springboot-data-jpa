package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestMapper;

    public CategoryController(CategoryService categoryService, ResponseDtoMapper<CategoryResponseDto,
            Category> categoryResponseMapper, RequestDtoMapper<CategoryRequestDto,
            Category> categoryRequestMapper) {
        this.categoryService = categoryService;
        this.categoryResponseMapper = categoryResponseMapper;
        this.categoryRequestMapper = categoryRequestMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryRequestMapper.mapToModel(requestDto));
        return categoryResponseMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryResponseMapper.mapToDto(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryRequestDto dto) {
        Category category = categoryRequestMapper.mapToModel(dto);
        category.setId(id);
        return categoryResponseMapper.mapToDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
