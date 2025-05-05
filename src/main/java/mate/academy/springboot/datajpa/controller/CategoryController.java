package mate.academy.springboot.datajpa.controller;

import java.util.Optional;
import javax.validation.Valid;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.responce.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.dto.responce.ProductResponseDto;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            return categoryMapper.mapToDto(category.get());
        }
        throw new RuntimeException("Can`t get category with this id: " + id);
    }

    @PutMapping("/create")
    public CategoryResponseDto create(@RequestBody @Valid CategoryRequestDto dto) {
        Category category = categoryMapper.mapToModel(dto);
        return categoryMapper.mapToDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto delete(@PathVariable Long id) {
        Optional<Category> category = categoryService.getById(id);
        category.ifPresent(categoryService::delete);
        throw new RuntimeException("Can`t delete category with this id: " + id);
    }

    @PostMapping("/update/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto dto) {
        if (categoryService.getById(id).isPresent()) {
            Category category = categoryMapper.mapToModel(dto);
            category.setId(id);
            return categoryMapper.mapToDto(categoryService.save(category));
        }
        throw new RuntimeException("Can`t update category with this id: " + id);
    }
}
