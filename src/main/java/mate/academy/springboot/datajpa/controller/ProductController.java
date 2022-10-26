package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public Product create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        return productService.save(product);
    }

    @GetMapping
    public Product findById(@RequestParam Long id) {
        return productService.getById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product updateById(@PathVariable Long id,
                              @RequestBody Product product) {
        product.setId(id);
        return productService.update(product);
    }

    @GetMapping("/byPriceBetween")
    public List<Product> findAllByPriceBetween(@RequestParam BigDecimal from,
                                           @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to);
    }
}
