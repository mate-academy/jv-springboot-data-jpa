package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
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
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<CategoryRequestDto, Category, CategoryResponseDto> dtoMapper;

    @PostMapping
    CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return dtoMapper.mapToDto(
                categoryService.save(
                        dtoMapper.mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    CategoryResponseDto get(@PathVariable Long id) {
        return dtoMapper.mapToDto(
                categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    CategoryResponseDto update(@PathVariable Long id,
                               @RequestBody CategoryRequestDto requestDto) {
        Category category = dtoMapper.mapToModel(requestDto);
        category.setId(id);
        return dtoMapper.mapToDto(categoryService.save(category));
    }
}
