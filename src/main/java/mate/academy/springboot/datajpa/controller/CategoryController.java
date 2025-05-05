package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
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
    private final RequestDtoMapper<Category, CategoryRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Category, CategoryResponseDto> responseDtoMapper;

    public CategoryController(CategoryService categoryService,
                              RequestDtoMapper<Category, CategoryRequestDto> requestDtoMapper,
                              ResponseDtoMapper<Category, CategoryResponseDto> responseDtoMapper) {
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping("/create")
    public CategoryResponseDto addCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return responseDtoMapper.mapToDto(
                categoryService.save(
                        requestDtoMapper.mapToModel(categoryRequestDto)));
    }

    @GetMapping("/get/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/update/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = requestDtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.update(category));
    }
}
