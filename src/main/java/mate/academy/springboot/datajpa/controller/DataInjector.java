package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataInjector {
    private final CategoryService categoryService;
    private final ProductService productService;

    public DataInjector(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/inject")
    public String inject() {
        Category shoes = new Category("shoes");
        Category phones = new Category("phones");
        List<Category> categories = List.of(shoes, phones);
        categories.forEach(categoryService::create);

        Product nike = new Product("Nike", BigDecimal.valueOf(150), shoes);
        Product puma = new Product("Puma", BigDecimal.valueOf(140), shoes);
        Product pixel = new Product("Google Pixel 6", BigDecimal.valueOf(1200), phones);
        Product samsung = new Product("Samsung S20", BigDecimal.valueOf(1230), phones);
        List<Product> products = List.of(nike, puma, pixel, samsung);
        products.forEach(productService::create);

        return "Data successfully injected";
    }
}
