package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InitController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public void init() {
        Category category = new Category();
        category.setName("drama");

        Category category1 = new Category();
        category1.setName("comedy");

        categoryService.create(category);
        categoryService.create(category1);

        Product product = new Product();
        product.setTitle("plak");
        product.setPrice(BigDecimal.valueOf(25.5));
        product.setCategory(category);

        Product product1 = new Product();
        product1.setTitle("lol");
        product1.setPrice(BigDecimal.valueOf(25.5));
        product1.setCategory(category1);

        Product product2 = new Product();
        product2.setTitle("kek");
        product2.setPrice(BigDecimal.valueOf(25.5));
        product2.setCategory(category1);

        productService.create(product);
        productService.create(product1);
        productService.create(product2);
    }
}
