package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.impl.CategoryRequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.impl.CategoryResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryResponseDtoMapper categoryResponseDtoMapper;
    private final CategoryRequestDtoMapper categoryRequestDtoMapper;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              CategoryResponseDtoMapper categoryResponseDtoMapper,
                              CategoryRequestDtoMapper categoryRequestDtoMapper) {
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

    @GetMapping
    public CategoryResponseDto getById(@RequestParam(name = "category_id") Long categoryId){
        return categoryResponseDtoMapper.toDto(
                categoryService.getById(categoryId)
        );
    }

    @DeleteMapping
    public void deleteById(@RequestParam(name = "category_id") Long categoryId) {
        categoryService.delete(categoryService.getById(categoryId));
    }

    @PutMapping
    public CategoryResponseDto update(@RequestParam(name = "category_id") Long categoryId,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDtoMapper.toModel(categoryRequestDto);
        category.setId(categoryId);
        return categoryResponseDtoMapper.toDto(categoryService.update(category));
    }
}
