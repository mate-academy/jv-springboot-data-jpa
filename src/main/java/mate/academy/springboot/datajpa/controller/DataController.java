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
public class DataController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public DataController(ProductService productService,
                          CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String inject() {
        Category category1 = new Category();
        category1.setName("Phones");
        final Category phoneCategory = categoryService.saveOrUpdate(category1);
        Category category2 = new Category();
        category2.setName("Cars");
        final Category carCategory = categoryService.saveOrUpdate(category2);

        Product product1 = new Product();
        product1.setTitle("Samsung");
        product1.setPrice(BigDecimal.valueOf(700L));
        product1.setCategory(phoneCategory);
        productService.saveOrUpdate(product1);
        Product product2 = new Product();
        product2.setTitle("Apple");
        product2.setPrice(BigDecimal.valueOf(1000L));
        product2.setCategory(phoneCategory);
        productService.saveOrUpdate(product2);
        Product product3 = new Product();
        product3.setTitle("Tesla");
        product3.setPrice(BigDecimal.valueOf(34000L));
        product3.setCategory(carCategory);
        productService.saveOrUpdate(product3);
        Product product4 = new Product();
        product4.setTitle("Mustang");
        product4.setPrice(BigDecimal.valueOf(21000L));
        product4.setCategory(carCategory);
        productService.saveOrUpdate(product4);

        return "Test data was injected!";
    }
}
