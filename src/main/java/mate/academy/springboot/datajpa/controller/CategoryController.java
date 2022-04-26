package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoRequestMapper<CategoryRequestDto, Category> dtoRequestMapper;
    private final DtoResponseMapper<CategoryResponseDto, Category> dtoResponseMapper;

    public CategoryController(CategoryService categoryService,
                              DtoRequestMapper<CategoryRequestDto, Category> dtoRequestMapper,
                              DtoResponseMapper<CategoryResponseDto, Category> dtoResponseMapper) {
        this.categoryService = categoryService;
        this.dtoRequestMapper = dtoRequestMapper;
        this.dtoResponseMapper = dtoResponseMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody @Valid CategoryRequestDto dto) {
        Category category = categoryService.save(dtoRequestMapper.toEntity(dto));
        return dtoResponseMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@RequestParam Long id) {
        Category categoryFromDb = categoryService.findById(id);
        return dtoResponseMapper.toDto(categoryFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        categoryService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CategoryRequestDto dto) {
        Category category = dtoRequestMapper.toEntity(dto);
        Category categoryUpdated = categoryService.update(id, category);
        return dtoResponseMapper.toDto(categoryUpdated);
    }
}
