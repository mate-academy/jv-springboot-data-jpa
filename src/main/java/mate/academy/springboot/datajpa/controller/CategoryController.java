package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.maper.DtoMapper;
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
    private final DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> mapper;

    public CategoryController(CategoryService categoryService, DtoMapper<Category,
            CategoryRequestDto, CategoryResponseDto> mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @PostMapping
    public CategoryResponseDto crate(@RequestBody CategoryRequestDto categoryRequestDto) {
        return mapper.modelToMap(categoryService
                .save(mapper.mapToModel(categoryRequestDto)));
    }

    @GetMapping("/{$id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return mapper.modelToMap(categoryService.gteCategoryById(id));
    }

    @DeleteMapping("/{$id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                              @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = mapper.mapToModel(categoryRequestDto);
        category.setId(id);
        categoryService.updateCategory(category);
        return mapper.modelToMap(category);
    }
}
