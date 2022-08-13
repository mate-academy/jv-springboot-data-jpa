package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inject")
public class InjectController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public String inject() {
        Category phone = new Category();
        phone.setName("phone");
        Category toy = new Category();
        toy.setName("toy");
        categoryService.save(phone);
        categoryService.save(toy);

        Product iphone10 = new Product();
        iphone10.setTitle("iPhone 10");
        iphone10.setPrice(BigDecimal.valueOf(1000));
        iphone10.setCategory(phone);
        productService.save(iphone10);

        Product iphone11 = new Product();
        iphone11.setTitle("iPhone 11");
        iphone11.setPrice(BigDecimal.valueOf(1100));
        iphone11.setCategory(phone);
        productService.save(iphone11);

        Product samsungS10 = new Product();
        samsungS10.setTitle("Samsung galaxy s10");
        samsungS10.setPrice(BigDecimal.valueOf(950));
        samsungS10.setCategory(phone);
        productService.save(samsungS10);

        Product dendy = new Product();
        dendy.setTitle("dendy classic");
        dendy.setPrice(BigDecimal.valueOf(250));
        dendy.setCategory(toy);
        productService.save(dendy);
        return "Inject, done.";
    }
}
