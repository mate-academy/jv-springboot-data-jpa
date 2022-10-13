package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
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
    private final DtoRequestMapper<Category, CategoryRequestDto> requestMapper;
    private final DtoResponseMapper<Category, CategoryResponseDto> responseMapper;

    public CategoryController(CategoryService categoryService,
                              DtoRequestMapper<Category, CategoryRequestDto> requestMapper,
                              DtoResponseMapper<Category, CategoryResponseDto> responseMapper) {
        this.categoryService = categoryService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        Category phones = new Category();
        phones.setName("Phones");
        categoryService.save(phones);

        Category tablets = new Category();
        tablets.setName("Tablets");
        categoryService.save(tablets);

        return "Categories were injected!";
    }

    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto) {
        Category category = requestMapper.toModel(requestDto);
        return responseMapper.toDto(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return responseMapper.toDto(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CategoryRequestDto requestDto) {
        Category category = requestMapper.toModel(requestDto);
        category.setId(id);
        return responseMapper.toDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "Category by id: " + id + " was deleted! ";
    }
}
