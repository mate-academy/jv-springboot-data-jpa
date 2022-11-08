package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InjectController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/inject")
    public String inject() {
        Category phone = new Category();
        phone.setName("Phone");
        phone = categoryService.save(phone);

        Product iphone = new Product();
        iphone.setTitle("iphone");
        iphone.setPrice(BigDecimal.valueOf(1200));
        iphone.setCategory(phone);
        iphone = productService.save(iphone);

        Product xiaomi = new Product();
        xiaomi.setTitle("xiaomi");
        xiaomi.setPrice(BigDecimal.valueOf(700));
        xiaomi.setCategory(phone);
        xiaomi = productService.save(xiaomi);

        Product samsung = new Product();
        samsung.setTitle("samsung");
        samsung.setPrice(BigDecimal.valueOf(1000));
        samsung.setCategory(phone);
        samsung = productService.save(samsung);
        return "Done!";
    }
}
