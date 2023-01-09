package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/inject")
    public void saveProducts() {
        Product product = new Product();
        product.setTitle("iPhone");
        product.setPrice(BigDecimal.valueOf(1399));

        productService.save(product);

        productService.findAll().forEach(System.out::println);
    }
}
