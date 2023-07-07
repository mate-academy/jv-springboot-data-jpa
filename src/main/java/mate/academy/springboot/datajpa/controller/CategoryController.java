package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
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

    @GetMapping("/inject")
    public String injectCategories() {
        Category cheap = new Category();
        cheap.setName("cheap");
        categoryService.save(cheap);

        Category expensive = new Category();
        expensive.setName("expensive");
        categoryService.save(expensive);

        return "Done";
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category savedCategory = categoryService
                .save(categoryMapper.mapToModel(categoryRequestDto));
        return categoryMapper.mapToResponseDto(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapper.mapToResponseDto(categoryService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                     @RequestBody CategoryRequestDto categoryRequestDto) {
        Category updatedCategory = categoryMapper.mapToModel(categoryRequestDto);
        updatedCategory.setId(id);
        categoryService.update(updatedCategory);
        return categoryMapper.mapToResponseDto(updatedCategory);
    }
}
