package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;
import mate.academy.springboot.datajpa.maper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> mapper;
    private final CategoryService categoryService;

    public CategoryController(DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> mapper, CategoryService categoryService) {
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        return mapper.toDto(categoryService.create(mapper.toModel(categoryRequestDto)));
    }

    @GetMapping("/{id}")
    CategoryResponseDto getById(@PathVariable Long id) {

        return mapper.toDto(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    CategoryResponseDto update(@PathVariable Long id,
                               @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = mapper.toModel(categoryRequestDto);
        category.setId(id);
        return mapper.toDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
