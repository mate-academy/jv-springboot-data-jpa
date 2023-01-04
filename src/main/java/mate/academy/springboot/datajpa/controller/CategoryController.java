package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;

    public CategoryController(
            CategoryService categoryService,
            RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper,
            ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper) {
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryDto) {
        Category category = requestDtoMapper.mapToModel(categoryDto);
        return responseDtoMapper.mapToDto(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryDto) {
        Category category = requestDtoMapper.mapToModel(categoryDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
