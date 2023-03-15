package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapperDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryMapperDto categoryMapperDto;
    private final CategoryService categoryService;

    public CategoryController(CategoryMapperDto categoryMapperDto,
                              CategoryService categoryService) {
        this.categoryMapperDto = categoryMapperDto;
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryMapperDto.mapToModel(requestDto));
        return categoryMapperDto.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryMapperDto.mapToDto(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PostMapping("/{id}")
    public CategoryResponseDto categoryResponseDto(@PathVariable Long id,
                                                   @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryMapperDto.mapToModel(requestDto);
        category.setId(id);
        return categoryMapperDto.mapToDto(categoryService.save(category));
    }
}
