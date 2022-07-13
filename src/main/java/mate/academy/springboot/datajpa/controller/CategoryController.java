package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
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

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryResponseDtoMapper.mapToDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto requestDto) {
        return categoryResponseDtoMapper.mapToDto(categoryService
                .add(categoryRequestDtoMapper.mapToModel(requestDto)));
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
