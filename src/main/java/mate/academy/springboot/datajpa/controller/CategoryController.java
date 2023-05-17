package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
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
    private final DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> dtoMapper;

    public CategoryController(CategoryService categoryService, DtoMapper<Category,
            CategoryRequestDto, CategoryResponseDto> dtoMapper) {
        this.categoryService = categoryService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(dtoMapper.mapToModel(categoryRequestDto));
        return dtoMapper.mapToDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = dtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        categoryService.update(category);
        return dtoMapper.mapToDto(category);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getById(@PathVariable Long categoryId) {
        return dtoMapper.mapToDto(categoryService.findById(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteById(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }
}
