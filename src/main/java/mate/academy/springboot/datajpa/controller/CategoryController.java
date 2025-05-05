package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return responseDtoMapper
                .mapToDto(categoryService
                        .save(requestDtoMapper
                                .mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return responseDtoMapper
                .mapToDto(categoryService
                        .findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = requestDtoMapper.mapToModel(requestDto);
        category.setId(id);
        categoryService.update(category);
        return responseDtoMapper.mapToDto(categoryService.update(category));
    }
}
