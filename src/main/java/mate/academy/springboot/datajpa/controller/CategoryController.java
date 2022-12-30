package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.request.CategoryRequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.response.CategoryResponseDtoMapper;
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
    private CategoryService categoryService;
    private CategoryRequestDtoMapper requestDtoMapper;
    private CategoryResponseDtoMapper responseDtoMapper;

    public CategoryController(CategoryService categoryService,
                              CategoryRequestDtoMapper requestDtoMapper,
                              CategoryResponseDtoMapper responseDtoMapper) {
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto requestDto) {
        return responseDtoMapper.mapToDto(categoryService.save(requestDtoMapper
                        .mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(categoryService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping
    public CategoryResponseDto update(@RequestBody CategoryRequestDto requestDto) {
        return responseDtoMapper.mapToDto(categoryService.update(requestDtoMapper
                        .mapToModel(requestDto)));
    }
}
