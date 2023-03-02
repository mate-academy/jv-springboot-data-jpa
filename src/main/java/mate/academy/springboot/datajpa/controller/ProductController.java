package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/inject")
    public void saveProducts() {
        Product product = new Product();
        product.setTitle("Iphone");
        product.setPrice(BigDecimal.valueOf(1399));
        product.setCategory(categoryService.get(1L));
        productService.save(product);

        Product product1 = new Product();
        product1.setTitle("IphoneX");
        product1.setPrice(BigDecimal.valueOf(1099));
        product1.setCategory(categoryService.get(1L));
        productService.save(product1);

        Product product2 = new Product();
        product2.setTitle("Iphone8");
        product2.setPrice(BigDecimal.valueOf(899));
        product2.setCategory(categoryService.get(1L));
        productService.save(product2);

        productService.findAllByCategory(categoryService.get(1L))
                .forEach(System.out::println);
    }
}
