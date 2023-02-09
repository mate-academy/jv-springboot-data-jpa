package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.dto.request.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.category.CategoryResponseDto;
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
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return mapToDto(categoryService.save(mapToModel(requestDto, null)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return mapToDto(categoryService.findById(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        return mapToDto(categoryService.save(mapToModel(requestDto, id)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    private Category mapToModel(CategoryRequestDto dto, Long id) {
        return categoryRequestDtoMapper.mapToModel(dto, id);
    }

    private CategoryResponseDto mapToDto(Category category) {
        return categoryResponseDtoMapper.mapToDto(category);
    }
}
