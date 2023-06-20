package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryResponseMapper;
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
    private final CategoryResponseMapper dtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryResponseMapper dtoMapper) {
        this.categoryService = categoryService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping()
    public void createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.create(dtoMapper.mapToObj(categoryRequestDto));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return dtoMapper.mapToDto(categoryService.getById(id));
    }

    @PutMapping("/update/{id}")
    public void updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto requestDto) {
        Category category = dtoMapper.mapToObj(requestDto);
        category.setId(id);
        categoryService.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
