package mate.academy.springboot.datajpa.controllers;

import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.services.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService categoryService, CategoryMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @GetMapping("/inject")
    public void create() {
        Category category = new Category();
        category.setName("milk");
        categoryService.save(category);

        Category category1 = new Category();
        category1.setName("fruits");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setName("vegetables");
        categoryService.save(category2);
    }

    @PostMapping
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        Category category = mapper.toModel(requestDto);
        return mapper.toResponseDto(categoryService
                .save(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return mapper.toResponseDto(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      CategoryRequestDto requestDto) {
        Category category = mapper.toModel(requestDto);
        category.setId(id);
        return mapper.toResponseDto(categoryService.update(category));
    }
}
