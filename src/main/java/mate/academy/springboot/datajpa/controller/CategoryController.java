package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.dto.mapper.CategoryDtoMapper;
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
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @GetMapping("/inject")
    public String saveCategories() {
        Category toy = new Category();
        toy.setName("TOY");
        categoryService.create(toy);

        Category cloth = new Category();
        cloth.setName("CLOTH");
        categoryService.create(cloth);

        Category food = new Category();
        food.setName("FOOD");
        categoryService.create(food);

        Category furniture = new Category();
        furniture.setName("FURNITURE");
        categoryService.create(furniture);

        return "Categories were added";
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getCategory(@PathVariable Long categoryId) {
        return categoryDtoMapper.toDto(categoryService.getById(categoryId));
    }

    @PostMapping("/add")
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryDtoMapper.toDto(categoryService
                .create(categoryDtoMapper.toModel(categoryRequestDto)));
    }

    @PutMapping("/{categoryId}")
    public void update(@PathVariable Long categoryId,
                       @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.update(categoryId,
                categoryDtoMapper.toModel(categoryRequestDto));
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
    }
}
