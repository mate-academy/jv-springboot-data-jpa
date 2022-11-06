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
@RequestMapping("/inject")
public class InjectController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public InjectController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public void inject() {
        Category categoryToy = new Category();
        categoryToy.setName("Toy");
        categoryService.create(categoryToy);
        Category categoryFood = new Category();
        categoryFood.setName("Food");
        categoryService.create(categoryFood);

        Product productBread = new Product();
        productBread.setTitle("Bread");
        productBread.setCategory(categoryFood);
        productBread.setPrice(BigDecimal.valueOf(30));
        productService.create(productBread);
        Product productButter = new Product();
        productButter.setTitle("Butter");
        productButter.setCategory(categoryFood);
        productButter.setPrice(BigDecimal.valueOf(70));
        productService.create(productButter);
        Product productBall = new Product();
        productBall.setTitle("Ball");
        productBall.setCategory(categoryToy);
        productBall.setPrice(BigDecimal.valueOf(500));
        productService.create(productBall);
        Product productCar = new Product();
        productCar.setTitle("Car");
        productCar.setCategory(categoryToy);
        productCar.setPrice(BigDecimal.valueOf(200));
        productService.create(productCar);
    }
}
