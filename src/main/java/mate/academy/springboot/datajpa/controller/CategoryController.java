package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final DtoMapper<RequestCategoryDto, ResponseCategoryDto, Category> categoryDtoMapper;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseCategoryDto create(@RequestBody @Valid RequestCategoryDto requestCategoryDto) {
        return categoryDtoMapper
                .mapToDto(categoryService.add(categoryDtoMapper.mapToModel(requestCategoryDto)));
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto get(@PathVariable Long id) {
        return categoryDtoMapper.mapToDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PatchMapping
    public ResponseCategoryDto update(@RequestBody @Valid RequestCategoryDto requestDto) {
        return categoryDtoMapper
                .mapToDto(categoryService.update(categoryDtoMapper.mapToModel(requestDto)));
    }
}
