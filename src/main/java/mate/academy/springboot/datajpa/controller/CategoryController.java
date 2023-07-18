package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    private RequestDtoMapper<CategoryRequestDto, Category> categoryRequestMapper;
    private ResponseDtoMapper<Category, CategoryResponseDto> categoryResponseMapper;

    @PostMapping
    public CategoryResponseDto create(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return categoryResponseMapper.mapToDto(categoryService.save(
                categoryRequestMapper.mapToModel(categoryRequestDto)));
    }

    @GetMapping
    public CategoryResponseDto get(@RequestParam @Positive Long id) {
        return categoryResponseMapper.mapToDto(categoryService.get(id));
    }

    @PutMapping
    public CategoryResponseDto update(@RequestParam @Positive Long id,
                                      @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return categoryResponseMapper.mapToDto(categoryService.save(category));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam @Positive Long id) {
        categoryService.remove(id);
    }
}
