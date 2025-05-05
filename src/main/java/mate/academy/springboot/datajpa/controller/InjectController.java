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
        phone.setName("Phones");
        phone = categoryService.save(phone);

        Category laptop = new Category();
        laptop.setName("Laptop");
        laptop = categoryService.save(laptop);

        Product iphoneX = new Product();
        iphoneX.setTitle("iphoneX");
        iphoneX.setCategory(phone);
        iphoneX.setPrice(BigDecimal.valueOf(600));

        Product iphoneSE = new Product();
        iphoneSE.setTitle("iphoneX");
        iphoneSE.setCategory(phone);
        iphoneSE.setPrice(BigDecimal.valueOf(900));

        Product macbook = new Product();
        macbook.setTitle("MacBook Pro 16");
        macbook.setCategory(laptop);
        macbook.setPrice(BigDecimal.valueOf(1200));

        return "Successfully injected!";
    }
}
