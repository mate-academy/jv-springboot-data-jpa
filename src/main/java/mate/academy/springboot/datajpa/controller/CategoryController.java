package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.MapperDto;
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
    private final CategoryService categoryService;
    private final MapperDto<CategoryRequestDto, CategoryResponseDto, Category> categoryMapperDto;

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll()
                .stream()
                .map(categoryMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.add(categoryMapperDto.toModel(categoryRequestDto));
        return categoryMapperDto.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryMapperDto.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategoryById(@PathVariable Long id,
                                          @RequestBody CategoryRequestDto categoryRequestDto) {

        Category category = categoryMapperDto.toModel(categoryRequestDto);
        category.setId(id);
        return categoryMapperDto.toDto(categoryService.update(category));
    }
}
