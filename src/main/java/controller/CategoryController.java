package controller;

import dto.request.CategoryRequestDto;
import dto.response.CategoryResponseDto;
import model.Category;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CategoryService;
import service.mapper.RequestDtoMapper;
import service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper;

    public CategoryController(CategoryService categoryService,
                              RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper,
                              ResponseDtoMapper<CategoryResponseDto, Category> responseDtoMapper) {
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @GetMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return responseDtoMapper.mapToDto(requestDtoMapper.mapToModel(requestDto));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = requestDtoMapper.mapToModel(requestDto);
        category.setId(id);
        return responseDtoMapper.mapToDto(categoryService.save(category));
    }
}
