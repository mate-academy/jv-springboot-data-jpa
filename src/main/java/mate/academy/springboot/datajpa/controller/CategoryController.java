package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ResponseDtoMapper<Category, CategoryResponseDto> responseDtoMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestDtoMapper;

    @PostMapping("/create")
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryResponseDto) {
        return responseDtoMapper.toDto(categoryService
                .save(requestDtoMapper.toModel(categoryResponseDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return responseDtoMapper.toDto(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.save(requestDtoMapper.toModel(categoryRequestDto));
        category.setId(id);
        return responseDtoMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping("/inject")
    public String inject() {
        Category toy = new Category();
        toy.setName("Toys");

        Category clothing = new Category();
        clothing.setName("Clothing");

        categoryService.save(toy);
        categoryService.save(clothing);
        return "Categories were injected";
    }
}
