package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.mapper.CategoryDtoMapper;
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
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto dto) {
        return categoryDtoMapper.modelToResponse(
                categoryService.create(
                        categoryDtoMapper.requestToModel(
                                dto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryDtoMapper.modelToResponse(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto dto) {
        Category category = categoryDtoMapper.requestToModel(dto);
        category.setId(id);
        return categoryDtoMapper.modelToResponse(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        categoryService.delete(id);
        return "Category with id " + id + " was deleted!";
    }
}
