package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;

    @Autowired
    public CategoryController(
            CategoryService categoryService,
            ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper,
            RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper) {
        this.categoryService = categoryService;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto category) {
        return categoryResponseDtoMapper.toDto(
                categoryService.save(categoryRequestDtoMapper.toModel(category))
        );
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable(name = "id") Long categoryId) {
        return categoryResponseDtoMapper.toDto(
                categoryService.getById(categoryId)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long categoryId) {
        categoryService.delete(categoryService.getById(categoryId));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable(name = "id") Long categoryId,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDtoMapper.toModel(categoryRequestDto);
        category.setId(categoryId);
        return categoryResponseDtoMapper.toDto(categoryService.save(category));
    }
}
