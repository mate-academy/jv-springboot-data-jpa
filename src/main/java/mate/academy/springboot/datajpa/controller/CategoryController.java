package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.ResponseCategoryDto;
import mate.academy.springboot.datajpa.dto.ResponseExceptionDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.exception.ControllerException;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping()
    public ResponseCategoryDto create(RequestCategoryDto requestCategoryDto) {
        Category category = categoryMapper.toModel(requestCategoryDto);
        return categoryMapper.toDto(categoryService.create(category));
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto update(@PathVariable Long id, RequestCategoryDto requestCategoryDto) {
        Category category = categoryMapper.toModel(requestCategoryDto);
        category.setId(id);
        return categoryMapper.toDto(categoryService.update(category));
    }

    @ExceptionHandler(ControllerException.class)
    private ResponseExceptionDto handleException(ControllerException e) {
        return new ResponseExceptionDto(e.getMessage());
    }
}
