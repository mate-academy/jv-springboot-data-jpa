package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.request.mappper.RequestMapper;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.response.mapper.ResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    private RequestMapper<Category, CategoryRequestDto> requestMapper;
    private ResponseMapper<CategoryResponseDto, Category> responseMapper;

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return responseMapper.mapToDto(categoryService.findById(id));
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto requestDto) {
        return responseMapper.mapToDto(categoryService.save(requestMapper.mapToModel(requestDto)));
    }

    @PutMapping
    public CategoryResponseDto update(@RequestParam Long categoryId,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category update = categoryService.update(categoryId, requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(update);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
