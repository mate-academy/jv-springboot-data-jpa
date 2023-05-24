package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/inject")
    public void saveProduct() {
        Product product = new Product();
        product.setTitle("Samsung");
        product.setPrice(BigDecimal.valueOf(1000));
        productService.save(product);
    }
}
