package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<Category, CategoryRequestDto> reqDtoMapper;
    private final ResponseDtoMapper<Category, CategoryResponseDto> respDtoMapper;

    @PostMapping("/create")
    CategoryResponseDto create(@RequestBody CategoryRequestDto reqDto) {
        Category category = categoryService.save(reqDtoMapper.toModel(reqDto));
        return respDtoMapper.toDto(category);
    }

    @GetMapping("/{id}")
    CategoryResponseDto getById(@PathVariable Long id) {
        return respDtoMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    boolean deleteById(@PathVariable Long id) {
        return categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryRequestDto reqDto) {
        Category category = reqDtoMapper.toModel(reqDto);
        category.setId(id);
        return respDtoMapper.toDto(categoryService.save(category));
    }
}
