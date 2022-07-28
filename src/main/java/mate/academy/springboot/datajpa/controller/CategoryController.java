package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponceDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapperDto;
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
    private final CategoryMapperDto categoryMapperDto;

    public CategoryController(CategoryService categoryService,
                              CategoryMapperDto categoryMapperDto) {
        this.categoryService = categoryService;
        this.categoryMapperDto = categoryMapperDto;
    }

    @PostMapping
    public CategoryResponceDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryMapperDto
                .mapToDto(categoryService.save(categoryMapperDto.mapToModel(categoryRequestDto)));
    }

    @GetMapping("/id")
    public CategoryResponceDto getById(@PathVariable Long id) {
        return categoryMapperDto.mapToDto(categoryService.getById(id));
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/id")
    public CategoryResponceDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryMapperDto
                .mapToDto(categoryService.update(categoryMapperDto.mapToModel(categoryRequestDto)));
    }
}
