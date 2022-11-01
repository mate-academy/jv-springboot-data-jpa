package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
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
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;

    public CategoryController(CategoryService categoryService,
                              ResponseDtoMapper<CategoryResponseDto,
                                      Category> responseDtoMapper,
                              RequestDtoMapper<CategoryRequestDto,
                                      Category> requestDtoMapper) {
        this.categoryService = categoryService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryService.save(requestDtoMapper.mapToModel(requestDto));
        return responseDtoMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return responseDtoMapper.mapToDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = requestDtoMapper.mapToModel(requestDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
