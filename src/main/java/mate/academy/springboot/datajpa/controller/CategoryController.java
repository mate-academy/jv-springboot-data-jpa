package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
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
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(requestDtoMapper.mapToModel(categoryRequestDto));
        return responseDtoMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = requestDtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.update(category));
    }
}
