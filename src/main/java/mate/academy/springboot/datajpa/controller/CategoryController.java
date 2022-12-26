package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;

    public CategoryController(CategoryService categoryService,
                              ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper,
                              RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper) {
        this.categoryService = categoryService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(categoryService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return responseDtoMapper.mapToDto(
                categoryService.save(
                        requestDtoMapper.mapToModel(categoryRequestDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CategoryRequestDto requestDto) {
        Category category = requestDtoMapper.mapToModel(requestDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.update(category));
    }
}
