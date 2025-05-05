package mate.academy.springboot.datajpa;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class BeanInjection {
    private final ProductService productService;
    private final CategoryService categoryService;

    public BeanInjection(ProductService productService,
                         CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        Category grocery = new Category();
        grocery.setName("grocery");
        Category phone = new Category();
        phone.setName("phone");
        Category meat = new Category();
        meat.setName("meat");
        grocery = categoryService.add(grocery);
        phone = categoryService.add(phone);
        meat = categoryService.add(meat);

        Product chicken = new Product();
        chicken.setTitle("chicken");
        chicken.setPrice(BigDecimal.valueOf(10));
        chicken.setCategory(meat);
        productService.add(chicken);

        Product buckweet = new Product();
        buckweet.setPrice(BigDecimal.valueOf(100));
        buckweet.setTitle("buckweet");
        buckweet.setCategory(grocery);
        productService.add(buckweet);

        Product pork = new Product();
        pork.setTitle("pork");
        pork.setPrice(BigDecimal.valueOf(80));
        pork.setCategory(meat);
        productService.add(pork);
    }
}
