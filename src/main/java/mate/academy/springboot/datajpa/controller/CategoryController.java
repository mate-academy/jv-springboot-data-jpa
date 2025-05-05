package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final CategoryServiceImp categoryService;
    private final CategoryMapper mapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(
            @Valid @RequestBody CategoryRequestDto request) {
        Category category = mapper.mapToEntity(request);
        Category savedCategory = categoryService.create(category);
        CategoryResponseDto response = mapper.mapToDto(savedCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> get(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        CategoryResponseDto response = mapper.mapToDto(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDto request) {
        Category category = mapper.mapToEntity(request).setId(id);
        Category updatedCategory = categoryService.update(category);
        CategoryResponseDto response = mapper.mapToDto(updatedCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
