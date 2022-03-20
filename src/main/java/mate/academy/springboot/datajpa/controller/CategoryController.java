package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryDto;
import mate.academy.springboot.datajpa.dto.CategoryDtoMapper;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService, CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public CategoryDto save(@RequestBody CategoryDto categoryDto) {
        return categoryDtoMapper.toDto(categoryService.save(categoryDtoMapper.toModel(categoryDto)));
    }

    @GetMapping(value = "/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryDtoMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable Long id) {
        categoryService.deleteDyId(id);
        return "Done!";
    }
}
