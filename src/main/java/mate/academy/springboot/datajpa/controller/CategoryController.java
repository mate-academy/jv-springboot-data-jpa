package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final DtoMapper<RequestCategoryDto, ResponseCategoryDto, Category> mapper;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseCategoryDto create(@RequestParam RequestCategoryDto categoryDto) {
        Category category = categoryService.save(mapper.mapToModel(categoryDto));
        return mapper.mapToDto(category);
    }

    @GetMapping
    public ResponseCategoryDto get(@PathVariable Long id) {
        return mapper.mapToDto(categoryService.getId(id));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PatchMapping
    public ResponseCategoryDto update(@RequestBody @Valid RequestCategoryDto dto) {
        return mapper.mapToDto(categoryService.update(mapper.mapToModel(dto)));
    }
}
