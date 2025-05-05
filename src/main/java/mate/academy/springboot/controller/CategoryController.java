package mate.academy.springboot.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.dto.request.CategoryRequestDto;
import mate.academy.springboot.dto.response.CategoryResponseDto;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.service.CategoryService;
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
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseMapper;

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto requestDto) {
        Category category = categoryService.save(requestMapper.toModel(requestDto));
        return responseMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return responseMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = requestMapper.toModel(requestDto);
        category.setId(id);
        return responseMapper.toDto(categoryService.save(category));
    }
}
