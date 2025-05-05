package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.CategoryDtoMapper;
import mate.academy.springboot.datajpa.model.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryDtoMapper mapper;

    @Autowired
    public CategoryController(CategoryService service, CategoryDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto dto) {
        return mapper.toResponseDto(service.save(mapper.toModel(dto)));
    }

    @GetMapping
    public CategoryResponseDto get(@RequestParam Long id) {
        return mapper.toResponseDto(service.get(id));
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return String.format("Category with id:%d has been deleted", id);
    }
}
