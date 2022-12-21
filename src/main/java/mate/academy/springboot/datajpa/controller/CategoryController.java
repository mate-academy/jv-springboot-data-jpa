package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
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
    private final CategoryService categoryService;
    private final DtoResponseMapper<CategoryResponseDto, Category> categoryResponseMapper;
    private final DtoRequestMapper<CategoryRequestDto, Category> categoryRequestMapper;

    public CategoryController(CategoryService categoryService,
                              DtoResponseMapper<CategoryResponseDto, Category>
                                      categoryResponseMapper,
                              DtoRequestMapper<CategoryRequestDto, Category>
                                      categoryRequestMapper) {
        this.categoryService = categoryService;
        this.categoryResponseMapper = categoryResponseMapper;
        this.categoryRequestMapper = categoryRequestMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryRequestMapper.fromDto(requestDto);
        categoryService.save(category);
        return categoryResponseMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return categoryResponseMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping
    public CategoryResponseDto update(@RequestParam Long id,
                                      @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryRequestMapper.fromDto(requestDto);
        category.setId(id);
        categoryService.update(category);
        return categoryResponseMapper.toDto(category);
    }
}
