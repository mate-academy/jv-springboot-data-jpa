package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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

    public CategoryController(
            CategoryService categoryService,
            ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper,
            RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper
    ) {
        this.categoryService = categoryService;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(
                categoryRequestDtoMapper.mapToModel(categoryRequestDto));
        return categoryResponseDtoMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryResponseDtoMapper.mapToDto(categoryService.getReferenceById(id));
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
        return categoryResponseDtoMapper.mapToDto(categoryService.update(category));
    }
}
