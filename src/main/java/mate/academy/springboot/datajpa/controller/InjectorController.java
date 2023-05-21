package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectorController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InjectorController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/inject")
    public void inject() {
        Category phoneCategory = new Category();
        phoneCategory.setName("Phones");
        categoryService.save(phoneCategory);

        Category laptopCategory = new Category();
        laptopCategory.setName("Laptops");
        categoryService.save(laptopCategory);

        Product phone = new Product();
        phone.setTitle("Xiaomi Note 5");
        phone.setCategory(phoneCategory);
        phone.setPrice(BigDecimal.valueOf(200));
        productService.save(phone);

        Product laptop = new Product();
        laptop.setTitle("Lenovo 505");
        laptop.setCategory(laptopCategory);
        laptop.setPrice(BigDecimal.valueOf(400));
        productService.save(laptop);
    }
}
