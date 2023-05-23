package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final DtoMapper<CategoryRequestDto, Category, CategoryResponseDto> categoryDtoMapper;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryDtoMapper.mapToDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryDtoMapper.mapToDto(categoryService
                .add(categoryDtoMapper.mapToModel(categoryRequestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryDtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return categoryDtoMapper.mapToDto(categoryService.add(category));
    }
}
