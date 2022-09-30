package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.dto.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    private final DtoRequestMapper<CategoryRequestDto, Category> categoryRequestMapper;
    private final DtoResponseMapper<CategoryResponseDto, Category> categoryResponseMapper;

    public CategoryController(CategoryService categoryService,
                              DtoRequestMapper<CategoryRequestDto, Category>
                                      categoryRequestMapper,
                              DtoResponseMapper<CategoryResponseDto, Category>
                                      categoryResponseMapper) {
        this.categoryService = categoryService;
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryRequestMapper.fromDto(requestDto));
        return categoryResponseMapper.toDto(category);
    }

    @GetMapping
    public CategoryResponseDto getById(@RequestParam Long id) {
        return categoryResponseMapper.toDto(categoryService.get(id)
                .orElseThrow(RuntimeException::new));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryRequestMapper.fromDto(requestDto);
        category.setId(id);
        categoryService.update(category);
        return categoryResponseMapper.toDto(category);
    }
}
