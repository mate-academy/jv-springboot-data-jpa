package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.resquest.CategoryRequestDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
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
    public CategoryResponseDto save(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(categoryMapper.mapToModel(categoryRequestDto));
        return categoryMapper.mapToDto(category);
    }

    @GetMapping()
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll().stream()
                .map(e -> categoryMapper.mapToDto(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                     @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return categoryMapper.mapToDto(categoryService.save(category));
    }
}
