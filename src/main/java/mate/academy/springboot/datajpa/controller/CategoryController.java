package mate.academy.springboot.datajpa.controller;

import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private RequestDtoMapper<CategoryRequestDto, Category> categoryRequestMapper;
    private ResponseDtoMapper<Category, CategoryResponseDto> categoryResponseMapper;

    public CategoryController(CategoryService categoryService,
                              RequestDtoMapper<CategoryRequestDto,
                                      Category> categoryRequestMapper,
                              ResponseDtoMapper<Category,
                                      CategoryResponseDto> categoryResponseMapper) {
        this.categoryService = categoryService;
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryResponseMapper.mapToDto(categoryService.save(
                categoryRequestMapper.mapToModel(categoryRequestDto)));
    }

    @GetMapping
    public CategoryResponseDto get(@RequestParam Long id) {
        return categoryResponseMapper.mapToDto(categoryService.get(id)
                .orElseThrow(() -> new NoSuchElementException("Can`t find category with id: "
                        + id)));
    }

    @PutMapping
    public CategoryResponseDto update(@RequestParam Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return categoryResponseMapper.mapToDto(categoryService.update(category));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Long id) {
        categoryService.remove(id);
    }
}
