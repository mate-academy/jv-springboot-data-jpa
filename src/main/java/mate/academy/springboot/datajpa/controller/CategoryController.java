package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
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
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;

    public CategoryController(CategoryService categoryService,
                              ResponseDtoMapper<CategoryResponseDto, Category>
                                      categoryResponseDtoMapper,
                              RequestDtoMapper<CategoryRequestDto, Category>
                                      categoryRequestDtoMapper) {
        this.categoryService = categoryService;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.create(categoryRequestDtoMapper.toModel(requestDto));
        return categoryResponseDtoMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.findById(id);
        category.setName(requestDto.getName());
        Category updatedCategory = categoryService.update(category);
        return categoryResponseDtoMapper.toDto(updatedCategory);
    }
}
