package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<CategoryRequestDto, CategoryResponseDto, Category> categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              DtoMapper<CategoryRequestDto, CategoryResponseDto, Category>
                                      categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryService.save(
                categoryDtoMapper.toModel(requestDto));
        return categoryDtoMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryDtoMapper.toModel(requestDto);
        category.setId(id);
        return categoryDtoMapper.toDto(categoryService.update(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryDtoMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
