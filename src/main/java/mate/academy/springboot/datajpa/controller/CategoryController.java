package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryRepository categoryRepository,
                              CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


}
