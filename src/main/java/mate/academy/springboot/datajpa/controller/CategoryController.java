package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
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
@RequestMapping(name = "/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;

    public CategoryController(CategoryService categoryService,
                              RequestDtoMapper<CategoryRequestDto,
                                      Category> categoryRequestDtoMapper,
                              ResponseDtoMapper<CategoryResponseDto,
                                      Category> categoryResponseDtoMapper) {
        this.categoryService = categoryService;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService
                .save(categoryRequestDtoMapper.mapToModel(categoryRequestDto));
        return categoryResponseDtoMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryResponseDtoMapper.mapToDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        categoryService.update(category);
        return categoryResponseDtoMapper.mapToDto(category);
    }
}
