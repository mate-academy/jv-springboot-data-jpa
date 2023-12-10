package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestMapper;
import mate.academy.springboot.datajpa.mapper.ResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RequestMapper<CategoryRequestDto, Category> requestMapper;
    private final ResponseMapper<CategoryResponseDto, Category> responseMapper;

    public CategoryController(CategoryService categoryService,
                              RequestMapper<CategoryRequestDto, Category> requestMapper,
                              ResponseMapper<CategoryResponseDto, Category> responseMapper) {
        this.categoryService = categoryService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(requestMapper.toModel(categoryRequestDto));
        return responseMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return responseMapper.toDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>("Resource deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                       @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.get(id);
        category.setName(categoryRequestDto.getName());
        return responseMapper.toDto(categoryService.update(category));
    }
}
