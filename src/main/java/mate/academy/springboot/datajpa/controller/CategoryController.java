package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(
            @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryMapper.mapToModel(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.mapToDto(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.mapToDto(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable Long id, @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryMapper.mapToModel(requestDto);
        category.setId(id);
        categoryService.save(category);
        return ResponseEntity.ok().body(categoryMapper.mapToDto(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
