package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
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
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.toDto(categoryService.create(categoryMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    CategoryResponseDto findById(@PathVariable(name = "id") Long id) {
        return categoryMapper.toDto(categoryService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable(name = "id") Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/update/{id}")
    CategoryResponseDto update(@PathVariable(name = "id") Long id,
                               @RequestBody CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setId(id);
        category.setName(requestDto.getName());
        return categoryMapper.toDto(categoryService.update(category));
    }
}
