package mate.academy.springboot.datajpa.config;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class Inject {
    private final CategoryService categoryService;
    private final ProductService productService;

    public Inject(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostConstruct
    public void injectData() {
        injectCategories();
        injectProduct();
    }

    public void injectCategories() {
        Category cheap = new Category();
        cheap.setName("cheap");
        categoryService.save(cheap);

        Category expensive = new Category();
        expensive.setName("expensive");
        categoryService.save(expensive);
    }

    public void injectProduct() {
        Product iphone1 = new Product();
        iphone1.setTitle("iPhone 1");
        iphone1.setPrice(BigDecimal.valueOf(100));
        iphone1.setCategory(categoryService.get(1L));
        productService.save(iphone1);

        Product iphone2 = new Product();
        iphone2.setTitle("iPhone 2");
        iphone2.setPrice(BigDecimal.valueOf(200));
        iphone2.setCategory(categoryService.get(1L));
        productService.save(iphone2);

        Product iphone3 = new Product();
        iphone3.setTitle("iPhone 3");
        iphone3.setPrice(BigDecimal.valueOf(300));
        iphone3.setCategory(categoryService.get(2L));
        productService.save(iphone3);

        Product iphone4 = new Product();
        iphone4.setTitle("iPhone 4");
        iphone4.setPrice(BigDecimal.valueOf(400));
        iphone4.setCategory(categoryService.get(2L));
        productService.save(iphone4);
    }
}
