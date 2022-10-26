package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public Category create(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return categoryRepository.save(category);
    }

    @GetMapping
    public Category findById(@RequestParam Long id) {
        return categoryRepository.findById(id).get();
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        categoryRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody Category category) {
        categoryRepository.update(category.getName(), id);
    }
}
