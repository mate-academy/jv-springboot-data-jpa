package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.entity.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
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
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryMapper.toEntity(requestDto);
        categoryService.save(category);
        return categoryMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return categoryMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(
            @RequestBody CategoryRequestDto requestDto,
            @PathVariable Long id
    ) {
        Category category = categoryMapper.toEntity(requestDto);
        category.setId(id);
        categoryService.save(category);
        return categoryMapper.toDto(category);
    }
}
