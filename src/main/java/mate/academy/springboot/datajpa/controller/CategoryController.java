package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mappers.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mappers.ResponseDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper,
                              ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper,
                              CategoryService categoryService) {
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = requestDtoMapper.mapToModel(categoryRequestDto);
        Category savedCategory = categoryService.save(category);
        return responseDtoMapper.mapToDto(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        responseDtoMapper.mapToDto(categoryService.get(id));
        return responseDtoMapper.mapToDto(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = requestDtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
