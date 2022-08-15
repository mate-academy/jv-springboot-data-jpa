package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
public class InjectController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public InjectController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String injectData() {
        Category phonesCategory = new Category();
        phonesCategory.setName("PHONES");
        categoryService.create(phonesCategory);

        Category laptopsCategory = new Category();
        laptopsCategory.setName("LAPTOPS");
        categoryService.create(laptopsCategory);

        Product asus = new Product();
        asus.setTitle("Asus Expert Book");
        asus.setPrice(BigDecimal.valueOf(1755));
        asus.setCategory(laptopsCategory);
        productService.create(asus);

        Product mac = new Product();
        mac.setTitle("Mac Pro M1");
        mac.setPrice(BigDecimal.valueOf(1755));
        mac.setCategory(laptopsCategory);
        productService.create(mac);

        Product iphone13 = new Product();
        iphone13.setTitle("iPhone 13Pro");
        iphone13.setPrice(BigDecimal.valueOf(1499));
        iphone13.setCategory(phonesCategory);
        productService.create(iphone13);

        Product iphone11 = new Product();
        iphone11.setTitle("iPhone 11");
        iphone11.setPrice(BigDecimal.valueOf(759));
        iphone11.setCategory(phonesCategory);
        productService.create(iphone11);

        Product iphone12Max = new Product();
        iphone12Max.setTitle("iPhone 12Max");
        iphone12Max.setPrice(BigDecimal.valueOf(1250));
        iphone12Max.setCategory(phonesCategory);
        productService.create(iphone12Max);

        List<Product> allBetweenPrice
                = productService.getAllBetweenPrice(BigDecimal.valueOf(1100),
                BigDecimal.valueOf(1700));
        System.out.println(allBetweenPrice);
        System.out.println("End: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss")));
        return "Success inject is done";
    }
}
