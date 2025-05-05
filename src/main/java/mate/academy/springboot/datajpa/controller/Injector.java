package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Injector {
    private final ProductService productService;
    private final CategoryService categoryService;

    public Injector(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/inject")
    public void saveProducts() {
        Product iphone = new Product();
        iphone.setTitle("iphone 14");
        iphone.setPrice(BigDecimal.valueOf(1000));
        Category phone = new Category();
        phone.setName("Phone");
        categoryService.save(phone);
        iphone.setCategory(phone);
        productService.save(iphone);

        Product ps4 = new Product();
        ps4.setTitle("Sony Play Station 4");
        ps4.setPrice(BigDecimal.valueOf(500));
        Category playStation = new Category();
        playStation.setName("PS");
        categoryService.save(playStation);
        ps4.setCategory(playStation);
        productService.save(ps4);

        Product bmw = new Product();
        bmw.setTitle("BMW i3");
        bmw.setPrice(BigDecimal.valueOf(50000));
        Category car = new Category();
        car.setName("Car");
        categoryService.save(car);
        bmw.setCategory(car);
        productService.save(bmw);
    }
}
