package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.CategoryDtoMapper;
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
    private CategoryService categoryService;
    private CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryDtoMapper.toModel(categoryRequestDto);
        return categoryDtoMapper.mapToDto(categoryService.save(category));
    }

    @GetMapping("/{id}")
    CategoryResponseDto getById(@PathVariable Long id) {
        return categoryDtoMapper.mapToDto(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    CategoryResponseDto update(@PathVariable Long id,
                               @RequestParam CategoryRequestDto requestDto) {
        Category category = categoryService.getById(id);
        category.setName(requestDto.getName());
        return categoryDtoMapper.mapToDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
