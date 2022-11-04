package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryRepository categoryRepository,
                              CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/create")
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getById(@PathVariable Long categoryId) {
        Category category = categoryRepository.getById(categoryId);
        return categoryMapper.toDto(category);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponseDto update(@PathVariable Long categoryId,
                                      @RequestParam
                                      CategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        category.setId(categoryId);
        return categoryMapper.toDto(category);
    }
}
