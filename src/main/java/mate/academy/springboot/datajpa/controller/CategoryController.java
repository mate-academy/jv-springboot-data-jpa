package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryDto;
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
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
        Category category = mapper.mapToEntity(dto);
        Category savedCategory = categoryService.create(category);
        CategoryDto resultDto = mapper.mapToDto(savedCategory);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable Long id) {
        CategoryDto resultDto = categoryService.findById(id).map(mapper::mapToDto).orElse(null);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        Category category = mapper.mapToEntity(dto);
        Category updatedCategory = categoryService.update(id, category);
        CategoryDto resultDto = mapper.mapToDto(updatedCategory);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
