package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
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
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final DtoMapper<CategoryRequestDto, CategoryResponseDto, Category> mapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody @Valid CategoryRequestDto dto) {
        Category category = service.add(mapper.mapToModel(dto));
        return mapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getByID(@PathVariable Long id) {
        Category category = service.getById(id);
        return mapper.mapToDto(category);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
            @RequestBody @Valid CategoryRequestDto dto) {
        return mapper.mapToDto(service.update(mapper.mapToModel(dto), id));
    }
}
