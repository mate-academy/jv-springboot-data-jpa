package mate.academy.springboot.datajpa.util;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInject {
    private ProductService productService;
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        Category category1 = new Category();
        category1.setName("Computers");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setName("Headsets");
        categoryService.save(category2);

        Category category3 = new Category();
        category3.setName("Tablets");
        categoryService.save(category3);

        Product product1 = new Product();
        product1.setTitle("Mac Book AirSuperProMax-99999");
        product1.setPrice(BigDecimal.valueOf(1_500));
        product1.setCategory(category1);
        productService.save(product1);

        Product product2 = new Product();
        product2.setTitle("Listen GooD");
        product2.setPrice(BigDecimal.valueOf(100));
        product2.setCategory(category2);
        productService.save(product2);

        Product product3 = new Product();
        product3.setTitle("HP GlobalPower");
        product3.setPrice(BigDecimal.valueOf(1_240));
        product3.setCategory(category1);
        productService.save(product3);

        Product product4 = new Product();
        product4.setTitle("Big Log Machine");
        product4.setPrice(BigDecimal.valueOf(560));
        product4.setCategory(category3);
        productService.save(product4);

        Product product5 = new Product();
        product5.setTitle("Small Book");
        product5.setPrice(BigDecimal.valueOf(800));
        product5.setCategory(category3);
        productService.save(product5);
    }
}
