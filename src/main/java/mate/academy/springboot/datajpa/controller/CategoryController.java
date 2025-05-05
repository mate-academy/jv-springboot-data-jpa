package mate.academy.springboot.datajpa.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<Category, RequestCategoryDto, ResponseCategoryDto> categoryMapper;

    @PostMapping
    public ResponseCategoryDto create(@RequestBody RequestCategoryDto requestCategoryDto) {
        Category category = categoryMapper.toModel(requestCategoryDto);
        return categoryMapper.toDto(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto update(@PathVariable Long id,
                                      @RequestBody RequestCategoryDto requestCategoryDto) {
        Category category = categoryMapper.toModel(requestCategoryDto);
        category.setId(id);
        return categoryMapper.toDto(categoryService.save(category));
    }

    @GetMapping
    public List<ResponseCategoryDto> getAll() {
        return categoryService.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }
}
