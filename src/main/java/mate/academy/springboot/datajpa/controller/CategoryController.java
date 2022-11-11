package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.requestdto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.responsedto.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public void add(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.addCategory(categoryMapper.toModel(categoryRequestDto));
    }

    @PutMapping
    public CategoryResponseDto update(@RequestBody CategoryRequestDto requestDto) {
        return categoryMapper.toDto(
                        categoryService.updateCategory(
                                categoryMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.findCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
