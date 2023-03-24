package mate.academy.springboot.datajpa.util;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductDataInjector {
    private static final int COUNT = 150;
    private static final Long MAX_PRICE = 500L;
    private static final String[] categories = {
            "Grocery",
            "Alcohol",
            "Drink",
            "Confectionery",
            "Baking",
            "Meat",
            "Fish",
            "Tobacco"
    };
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostConstruct
    public void init() {
        for (String category : categories) {
            categoryService.save(Category.builder()
                    .name(category).build());
        }
        for (int i = 1; i <= COUNT; i++) {
            productService.save(Product.builder()
                    .title("Product#" + i)
                    .price(BigDecimal.valueOf(new Random().nextLong(MAX_PRICE) + 1L))
                    .category(categoryService.findById(new Random()
                            .nextLong(categories.length) + 1L))
                    .build());
        }
    }
}
