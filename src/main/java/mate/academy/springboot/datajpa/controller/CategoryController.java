package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.impl.request.CategoryDtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.impl.response.CategoryDtoResponseMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoRequestMapper categoryDtoRequestMapper;
    private final CategoryDtoResponseMapper categoryDtoResponseMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoRequestMapper categoryDtoRequestMapper,
                              CategoryDtoResponseMapper categoryDtoResponseMapper) {
        this.categoryService = categoryService;
        this.categoryDtoRequestMapper = categoryDtoRequestMapper;
        this.categoryDtoResponseMapper = categoryDtoResponseMapper;
    }

    @PostMapping()
    public CategoryResponseDto add(@RequestBody CategoryRequestDto dto) {
        Category category = categoryService.save(categoryDtoRequestMapper.fromDto(dto));
        return categoryDtoResponseMapper.toDto(category);
    }

    @GetMapping()
    public CategoryResponseDto get(@RequestParam Long id) {
        return categoryDtoResponseMapper.toDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryRequestDto dto) {
        Category category = categoryDtoRequestMapper.fromDto(dto);
        category.setId(id);
        return categoryDtoResponseMapper.toDto(categoryService.update(category));
    }
}
