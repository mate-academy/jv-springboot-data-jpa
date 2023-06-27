package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
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

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<CategoryResponseDto, CategoryRequestDto, Category> dtoMapper;

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return dtoMapper
                .mapToDto(categoryService.getById(id));
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto requestDto) {
        return dtoMapper
                .mapToDto(categoryService.create(dtoMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = dtoMapper.mapToModel(requestDto);
        category.setId(id);
        return dtoMapper.mapToDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
