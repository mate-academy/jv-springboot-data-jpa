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
            ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper
    ) {
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(requestDtoMapper.mapToModel(categoryRequestDto));
        return responseDtoMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return responseDtoMapper.mapToDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                     @RequestBody CategoryRequestDto requestDto) {
        Category category = requestDtoMapper.mapToModel(requestDto);
        category.setId(id);
        Category updatedCategory = categoryService.save(category);
        return responseDtoMapper.mapToDto(updatedCategory);
    }
}
