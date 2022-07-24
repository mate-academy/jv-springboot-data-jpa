package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.requestdto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.responsedto.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
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
    private final CategoryMapper categoryMapperDto;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapperDto = categoryMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryMapperDto.mapToDto(categoryService
                .save(categoryMapperDto.mapToModel(categoryRequestDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryMapperDto.mapToDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.remove(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService
                .update(categoryMapperDto.mapToModel(categoryRequestDto));
        return categoryMapperDto.mapToDto(categoryService.update(category));
    }
}
