package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryMapper.toModel(requestDto));
        return categoryMapper.toResponseDto(category);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getById(@PathVariable Long categoryId) {
        return categoryMapper.toResponseDto(categoryService.get(categoryId));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
            @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        category.setId(id);
        categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.remove(id);
    }

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.findAll()
              .stream()
              .map(categoryMapper::toResponseDto)
              .collect(Collectors.toList());
    }
}
