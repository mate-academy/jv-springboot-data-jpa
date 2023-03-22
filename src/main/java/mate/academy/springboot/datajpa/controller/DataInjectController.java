package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class DataInjectController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public DataInjectController(CategoryService categoryService,
                                ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String inject() {
        Category laptop = new Category();
        laptop.setName(Category.Name.LAPTOP);
        Category phone = new Category();
        phone.setName(Category.Name.PHONE);
        categoryService.save(laptop);
        categoryService.save(phone);
        Product iphone10 = new Product();
        iphone10.setTitle("iPhone 10");
        iphone10.setPrice(BigDecimal.valueOf(100));
        iphone10.setCategory(phone);
        Product iphone11 = new Product();
        iphone11.setTitle("iPhone 11");
        iphone11.setPrice(BigDecimal.valueOf(500));
        iphone11.setCategory(phone);
        Product iphone12 = new Product();
        iphone12.setTitle("iPhone 12");
        iphone12.setPrice(BigDecimal.valueOf(1000));
        iphone12.setCategory(phone);
        productService.save(iphone10);
        productService.save(iphone11);
        productService.save(iphone12);
        Product macbookAir = new Product();
        macbookAir.setTitle("Macbook Air");
        macbookAir.setPrice(BigDecimal.valueOf(40000));
        macbookAir.setCategory(laptop);
        Product macbookPro = new Product();
        macbookPro.setTitle("Macbook Pro");
        macbookPro.setPrice(BigDecimal.valueOf(80000));
        macbookPro.setCategory(laptop);
        Product macbookAirM1 = new Product();
        macbookAirM1.setTitle("Macbook Air M1");
        macbookAirM1.setPrice(BigDecimal.valueOf(30000));
        macbookAirM1.setCategory(laptop);
        productService.save(macbookAir);
        productService.save(macbookPro);
        productService.save(macbookAirM1);
        return "Injected";
    }
}
