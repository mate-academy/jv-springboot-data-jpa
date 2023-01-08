package mate.academy.springboot.datajpa.controller;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoResponseMapper<CategoryResponseDto, Category> dtoResponseMapper;
    private final DtoRequestMapper<CategoryRequestDto, Category> dtoRequestMapper;

    public CategoryController(CategoryService categoryService,
                              DtoResponseMapper<CategoryResponseDto, Category> dtoResponseMapper,
                              DtoRequestMapper<CategoryRequestDto, Category> dtoRequestMapper) {
        this.categoryService = categoryService;
        this.dtoResponseMapper = dtoResponseMapper;
        this.dtoRequestMapper = dtoRequestMapper;
    }

    @PostMapping
    public CategoryResponseDto addCategory(@RequestBody CategoryRequestDto requestDto) {
        Category category = dtoRequestMapper.toModel(requestDto);
        return dtoResponseMapper.toDto(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategory(@PathVariable Long id) {
        return dtoResponseMapper.toDto(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CategoryRequestDto requestDto) {
        Category category = dtoRequestMapper.toModel(requestDto);
        category.setId(id);
        return dtoResponseMapper.toDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
