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
public class DataController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public DataController(ProductService productService,
                          CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String inject() {
        Category category1 = new Category();
        category1.setName("Birds");
        final Category birdsCategory = categoryService.saveOrUpdate(category1);
        Category category2 = new Category();
        category2.setName("Cats");
        final Category catsCategory = categoryService.saveOrUpdate(category2);

        Product product1 = new Product();
        product1.setTitle("Pigeon");
        product1.setPrice(BigDecimal.valueOf(10L));
        product1.setCategory(birdsCategory);
        productService.saveOrUpdate(product1);
        Product product2 = new Product();
        product2.setTitle("Ara");
        product2.setPrice(BigDecimal.valueOf(1000L));
        product2.setCategory(birdsCategory);
        productService.saveOrUpdate(product2);
        Product product3 = new Product();
        product3.setTitle("Siamese");
        product3.setPrice(BigDecimal.valueOf(3000L));
        product3.setCategory(catsCategory);
        productService.saveOrUpdate(product3);
        Product product4 = new Product();
        product4.setTitle("Sphinx");
        product4.setPrice(BigDecimal.valueOf(4000L));
        product4.setCategory(catsCategory);
        productService.saveOrUpdate(product4);

        return "Deployed test data";
    }
}
