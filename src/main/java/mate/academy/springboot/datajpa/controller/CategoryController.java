package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    private DtoMapper<Category, CategoryResponseDto, CategoryRequestDto> mapper;

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return mapper.toDto(categoryService.find(id));
    }

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto dto) {
        Category category = mapper.toModel(dto);
        return mapper.toDto(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CategoryRequestDto dto, @PathVariable Long id) {
        Category category = mapper.toModel(dto);
        category.setId(id);
        categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
