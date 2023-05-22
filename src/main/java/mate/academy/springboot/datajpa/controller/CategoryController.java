package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private CategoryMapper categoryMapper;
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto addCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryMapper.modelToDto(
                categoryService.save(categoryMapper.dtoToModel(categoryRequestDto))
        );
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryMapper.modelToDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public CategoryResponseDto updateCategoryById(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryMapper.modelToDto(
                categoryService.updateById(id, categoryMapper.dtoToModel(categoryRequestDto))
        );
    }
}
