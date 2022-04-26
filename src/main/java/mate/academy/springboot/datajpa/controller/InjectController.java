package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
@RequiredArgsConstructor
public class InjectController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping
    public String injectProducts() {
        int productAmount = 10;
        for (int i = 1; i <= productAmount; i++) {
            Category category = new Category();
            category.setName("Category #" + i);
            category = categoryRepository.save(category);

            Product product = new Product();
            product.setTitle("Product #" + i);
            product.setPrice(BigDecimal.valueOf(i * 13 + 13.5));
            product.setCategory(category);
            productRepository.save(product);
        }
        return productAmount + " products have been injected";
    }
}
