package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.dto.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
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
    private final CategoryService service;
    private final DtoRequestMapper<CategoryRequestDto, Category> requestMapper;
    private final DtoResponseMapper<CategoryResponseDto, Category> responseMapper;

    public CategoryController(CategoryService service,
                              DtoRequestMapper<CategoryRequestDto, Category> requestMapper,
                              DtoResponseMapper<CategoryResponseDto, Category> responseMapper) {
        this.service = service;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    CategoryResponseDto add(@RequestBody CategoryRequestDto dto) {
        Category category = service.save(requestMapper.fromDto(dto));
        return responseMapper.toDto(category);
    }

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody CategoryRequestDto dto) {
        Category category = requestMapper.fromDto(dto);
        category.setId(id);
        service.save(category);
    }

    @GetMapping("/{id}")
    CategoryResponseDto get(@PathVariable Long id) {
        Category category = service.get(id);
        return responseMapper.toDto(category);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
