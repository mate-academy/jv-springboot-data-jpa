package mate.academy.springboot.datajpa.controllers;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.services.CategoryService;
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
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody
                                      CategoryRequestDto requestDto) {
        Category category = mapper.toModel(requestDto);
        return mapper.toResponseDto(categoryService
                .save(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return mapper.toResponseDto(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody
                                      CategoryRequestDto requestDto) {
        Category category = mapper.toModel(requestDto);
        category.setId(id);
        return mapper.toResponseDto(categoryService.update(category));
    }
}
