package mate.academy.springboot.datajpa.controller;

import java.util.List;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public CategoryResponseDto create(
            @RequestBody @Valid CategoryRequestDto dto) {
        return categoryService.save(dto);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                     @RequestBody CategoryRequestDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        categoryService.remove(id);
    }

    @GetMapping("/add")
    public void addCategories() {
        CategoryRequestDto gross = new CategoryRequestDto();
        gross.setName("GROSS");
        categoryService.save(gross);

        CategoryRequestDto fruit = new CategoryRequestDto();
        fruit.setName("FRUIT");
        categoryService.save(fruit);

        CategoryRequestDto veg = new CategoryRequestDto();
        veg.setName("VEGETABLES");
        categoryService.save(veg);
    }
}

