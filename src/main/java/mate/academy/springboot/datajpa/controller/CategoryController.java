package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.response.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mappers.CategoryMapper;
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
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryController(CategoryMapper categoryMapper,
                              CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll()
                .stream()
                .map(categoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(categoryMapper.mapToModel(categoryRequestDto));
        return categoryMapper.mapToDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        Category save = categoryService.save(category);
        return categoryMapper.mapToDto(save);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryMapper.mapToDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
