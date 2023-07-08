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
public class InjectController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public InjectController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String injectData() {
        Category category1 = new Category();
        category1.setName("Phones");
        categoryService.save(category1);
        Category category2 = new Category();
        category2.setName("LapTops");
        categoryService.save(category2);

        Product product1 = new Product();
        product1.setName("Iphone X");
        product1.setPrice(BigDecimal.valueOf(600));
        product1.setCategory(category1);
        productService.save(product1);

        Product product2 = new Product();
        product2.setName("Iphone 12");
        product2.setPrice(BigDecimal.valueOf(800));
        product2.setCategory(category1);
        productService.save(product2);

        Product product3 = new Product();
        product3.setName("LapTop Appple Pro");
        product3.setPrice(BigDecimal.valueOf(1100));
        product3.setCategory(category2);
        productService.save(product3);

        System.out.println(productService.findAllByPriceBetween(
                BigDecimal.valueOf(800),BigDecimal.valueOf(1100)));
        System.out.println(productService.findAllProductsByCategory(1L));
        return "Done";
    }
}
