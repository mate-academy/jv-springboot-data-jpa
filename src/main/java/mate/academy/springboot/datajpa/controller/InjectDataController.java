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
public class InjectDataController {
    private CategoryService categoryService;
    private ProductService productService;

    public InjectDataController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String inject() {
        Category cat1 = new Category();
        cat1.setName("phones");
        Category phones = categoryService.create(cat1);

        Product iphone10 = new Product();
        iphone10.setPrice(BigDecimal.valueOf(500));
        iphone10.setTitle("iPhone 10");
        iphone10.setCategory(phones);
        productService.create(iphone10);

        Product iphone11 = new Product();
        iphone11.setPrice(BigDecimal.valueOf(700));
        iphone11.setTitle("iPhone 11");
        iphone11.setCategory(phones);
        productService.create(iphone11);

        Product iphone12 = new Product();
        iphone12.setPrice(BigDecimal.valueOf(1100));
        iphone12.setTitle("iPhone 12");
        iphone12.setCategory(phones);
        productService.create(iphone12);

        Product samsung = new Product();
        samsung.setPrice(BigDecimal.valueOf(500));
        samsung.setTitle("Samsung A32");
        samsung.setCategory(phones);
        productService.create(samsung);

        Category cat2 = new Category();
        cat2.setName("laptops");
        Category laptops = categoryService.create(cat2);

        Product asus = new Product();
        asus.setPrice(BigDecimal.valueOf(1500));
        asus.setTitle("Asus vivobook");
        asus.setCategory(laptops);
        productService.create(asus);

        Product alien = new Product();
        alien.setPrice(BigDecimal.valueOf(1500));
        alien.setTitle("Alien");
        alien.setCategory(laptops);
        productService.create(alien);

        return "Data was injected";
    }
}
