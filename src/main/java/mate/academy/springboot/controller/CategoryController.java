package mate.academy.springboot.controller;

import mate.academy.springboot.dto.request.CategoryUpdateRequestDto;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.dto.request.CategoryRequestDto;
import mate.academy.springboot.dto.response.CategoryResponseDto;
import mate.academy.springboot.service.CategoryService;
import mate.academy.springboot.service.mapper.RequestDtoMapper;
import mate.academy.springboot.service.mapper.ResponseDtoMapper;
import mate.academy.springboot.service.mapper.UpdateRequestDtoMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<Category, CategoryRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Category, CategoryResponseDto> responseDtoMapper;
    private final UpdateRequestDtoMapper<Category, CategoryUpdateRequestDto> updateRequestDtoMapper;

    public CategoryController(CategoryService categoryService,
                              RequestDtoMapper<Category, CategoryRequestDto> requestDtoMapper,
                              ResponseDtoMapper<Category, CategoryResponseDto> responseDtoMapper,
                              UpdateRequestDtoMapper<Category, CategoryUpdateRequestDto> updateRequestDtoMapper) {
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.updateRequestDtoMapper = updateRequestDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(requestDtoMapper.toModel(categoryRequestDto));
        return responseDtoMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.toDto(categoryService.getById(id));
    }

    @PutMapping
    public CategoryResponseDto update(
            @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = updateRequestDtoMapper.toModelUpdate(categoryUpdateRequestDto);
        Category categoryAfterUpdate
                = categoryService.update(category);
        return responseDtoMapper.toDto(categoryAfterUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
