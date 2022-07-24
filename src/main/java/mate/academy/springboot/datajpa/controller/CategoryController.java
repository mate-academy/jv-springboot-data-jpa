package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
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
    private final DtoResponseMapper<CategoryResponseDto, Category> dtoResponseMapper;
    private final DtoRequestMapper<CategoryRequestDto, Category> dtoRequestMapper;

    public CategoryController(CategoryService categoryService, DtoResponseMapper<CategoryResponseDto, Category> dtoResponseMapper, DtoRequestMapper<CategoryRequestDto, Category> dtoRequestMapper) {
        this.categoryService = categoryService;
        this.dtoResponseMapper = dtoResponseMapper;
        this.dtoRequestMapper = dtoRequestMapper;
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return dtoResponseMapper.toDto(categoryService.getById(id));
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return dtoResponseMapper.toDto(categoryService.save(dtoRequestMapper.fromDto(categoryRequestDto)));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = dtoRequestMapper.fromDto(categoryRequestDto);
        category.setId(id);
        return dtoResponseMapper.toDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
