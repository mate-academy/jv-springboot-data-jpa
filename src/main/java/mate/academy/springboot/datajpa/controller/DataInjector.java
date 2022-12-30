package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.Random;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class DataInjector {
    private static final String[] BRAND = new String[] {"Apple", "Samsung", "Huawei"};
    private ProductService productService;
    private CategoryService categoryService;

    public DataInjector(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String dataInject() {
        Category phone = new Category();
        phone.setName("Phone");
        categoryService.save(phone);
        Category laptop = new Category();
        laptop.setName("Laptop");
        categoryService.save(laptop);

        for (int i = 10; i < 15; i++) {
            Product product = new Product();
            product.setCategory(phone);
            product.setPrice(new BigDecimal(new Random().nextInt(1000, 1200)));
            product.setTitle(BRAND[new Random().nextInt(3)]);
            productService.save(product);
        }

        for (int i = 20; i < 25; i++) {
            Product product = new Product();
            product.setCategory(laptop);
            product.setTitle(BRAND[new Random().nextInt(3)]);
            product.setPrice(new BigDecimal(new Random().nextInt(1500, 2200)));
            productService.save(product);
        }

        return "Data injected!";
    }
}
