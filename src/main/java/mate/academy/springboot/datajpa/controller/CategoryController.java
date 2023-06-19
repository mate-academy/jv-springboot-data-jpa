package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mappers.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final DtoMapper<Category, CategoryResponseDto, CategoryRequestDto> dtoDtoMapper;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(DtoMapper<Category, CategoryResponseDto,
            CategoryRequestDto> dtoDtoMapper, CategoryService categoryService) {
        this.dtoDtoMapper = dtoDtoMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = dtoDtoMapper.mapToModel(categoryRequestDto);
        Category savedCategory = categoryService.save(category);
        return dtoDtoMapper.mapToDto(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        dtoDtoMapper.mapToDto(categoryService.get(id));
        return dtoDtoMapper.mapToDto(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = dtoDtoMapper.mapToModel(categoryRequestDto);
        category.setId(id);
        return dtoDtoMapper.mapToDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
