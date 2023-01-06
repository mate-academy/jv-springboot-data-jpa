package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class BootstrapController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public BootstrapController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/init")
    public void bootstrapData() {
        Category phoneCategory = new Category();
        phoneCategory.setName("phones");
        categoryService.create(phoneCategory);

        Category carCategory = new Category();
        carCategory.setName("cars");
        categoryService.create(carCategory);

        Product phone1 = new Product();
        phone1.setTitle("iPhone");
        phone1.setPrice(BigDecimal.valueOf(300));
        phone1.setCategory(phoneCategory);
        productService.create(phone1);

        Product phone2 = new Product();
        phone2.setTitle("Samsung");
        phone2.setPrice(BigDecimal.valueOf(250));
        phone2.setCategory(phoneCategory);
        productService.create(phone2);

        Product phone3 = new Product();
        phone3.setTitle("Sony");
        phone3.setPrice(BigDecimal.valueOf(350));
        phone3.setCategory(phoneCategory);
        productService.create(phone3);

        Product car1 = new Product();
        car1.setTitle("Skoda");
        car1.setPrice(BigDecimal.valueOf(25000));
        car1.setCategory(carCategory);
        productService.create(car1);

        Product car2 = new Product();
        car2.setTitle("Volvo");
        car2.setPrice(BigDecimal.valueOf(45000));
        car2.setCategory(carCategory);
        productService.create(car2);
    }
}
