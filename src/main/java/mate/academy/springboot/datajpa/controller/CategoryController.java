package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper,
                              ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper) {
        this.categoryService = categoryService;
        this.categoryRequestDtoMapper = requestDtoMapper;
        this.categoryResponseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryRequestDtoMapper.mapToModel(requestDto));
        return categoryResponseDtoMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryResponseDtoMapper.mapToDto(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryRequestDtoMapper.mapToModel(requestDto);
        category.setId(id);
        return categoryResponseDtoMapper.mapToDto(categoryService.save(category));
    }
}
