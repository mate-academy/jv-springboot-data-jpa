package mate.academy.springboot.datajpa.controller;

import jakarta.annotation.PostConstruct;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.impl.CategoryDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
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
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryDtoMapper categoryDtoMapper) {
        this.categoryService = categoryService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostConstruct
    public void saveCategory() {
        Category phone = new Category();
        phone.setName("Phones");
        categoryService.save(phone);

        Category videoCard = new Category();
        videoCard.setName("Video cards");
        categoryService.save(videoCard);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryDtoMapper.toDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryDtoMapper.toDto(categoryService.save(categoryDtoMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                     @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryDtoMapper.toModel(requestDto);
        category.setId(id);
        categoryService.update(category);
        return categoryDtoMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
