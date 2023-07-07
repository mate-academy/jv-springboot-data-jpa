package mate.academy.springboot.datajpa.controller;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.exception.DbException;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.entity.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.hibernate.PropertyValueException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DtoMapper<Category, CategoryResponseDto, CategoryRequestDto> mapper;

    @PostMapping
    public CategoryResponseDto saveCategory(@RequestBody CategoryRequestDto requestDto) {
        return mapper.mapToDto(categoryService.save(mapper.mapToEntity(requestDto)));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return mapper.mapToDto(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto requestDto) {
        Category category = mapper.mapToEntity(requestDto);
        category.setId(id);
        return mapper.mapToDto(categoryService.update(category));
    }

    @ExceptionHandler(value = DbException.class)
    public String handleDbException(DbException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = PropertyValueException.class)
    public String handlePropertyValueException(PropertyValueException ex) {
        return "Can`t add category to database. "
                + "Field \"" + ex.getPropertyName() + "\" should not be null";
    }
}
