package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class InjectorController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InjectorController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/inject")
    public void inject() {
        Category fruitCategory = new Category();
        fruitCategory.setName("Fruits");

        categoryService.save(fruitCategory);

        Product apple = new Product();
        apple.setTitle("apple");
        apple.setCategory(fruitCategory);
        apple.setPrice(BigDecimal.valueOf(10));

        productService.save(apple);
    }
}
