package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoResponseMapper<CategoryResponseDto, Category> categoryResponseDto;
    private final DtoRequestMapper<CategoryRequestDto,Category> categoryRequestDto;

    public CategoryController(CategoryService categoryService,
                              DtoResponseMapper<CategoryResponseDto, Category> categoryResponseDto,
                              DtoRequestMapper<CategoryRequestDto, Category> categoryRequestDto) {
        this.categoryService = categoryService;
        this.categoryResponseDto = categoryResponseDto;
        this.categoryRequestDto = categoryRequestDto;
    }

    @PostMapping("/create")
    public CategoryResponseDto create(@RequestBody CategoryRequestDto dto) {
        Category category = categoryService.create(categoryRequestDto.fromDto(dto));
        return categoryResponseDto.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return categoryResponseDto.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryRequestDto dto) {

        Category category = categoryService.get(id);
        category.setName(dto.getName());
        categoryService.update(category);
        return categoryResponseDto.toDto(category);
    }

    @DeleteMapping("/{id}")
    public CategoryResponseDto delete(@PathVariable Long id) {
        Category category = categoryService.get(id);
        categoryService.delete(category);
        return categoryResponseDto.toDto(category);
    }

}
