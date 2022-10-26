package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class injectController {
    private final ProductRepository productController;
    private final CategoryRepository categoryRepository;

    public injectController(ProductRepository productController,
                            CategoryRepository categoryRepository) {
        this.productController = productController;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/inject")
    private String inject() {
        Category category = new Category();
        category.setName("Luxury");
        categoryRepository.save(category);

        Product product = new Product();
        product.setCategory(category);
        product.setPrice(BigDecimal.valueOf(24.99));
        product.setTitle("Nuts");
        productController.save(product);
        return "Hello";
    }

}
