package mate.academy.springboot.datajpa.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import mate.academy.springboot.datajpa.services.CategoryService;
import mate.academy.springboot.datajpa.services.ProductService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class InjectController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/inject")
    public String inject() {
        Category product = new Category();
        product.setName("products");
        product = categoryService.create(product);

        Category toys = new Category();
        toys.setName("toys");
        toys = categoryService.create(toys);

        Category drinks = new Category();
        drinks.setName("drinks");
        drinks = categoryService.create(drinks);

        Category smartPhone = new Category();
        smartPhone.setName("smartPhone");
        smartPhone = categoryService.create(smartPhone);

        Product pizza = new Product();
        pizza.setCategory(product);
        pizza.setPrice(BigDecimal.valueOf(1.4));
        pizza.setTitle("pizza");
        pizza = productService.create(pizza);

        Product bear = new Product();
        bear.setCategory(toys);
        bear.setPrice(BigDecimal.valueOf(2.1));
        bear.setTitle("bear");
        bear = productService.create(bear);

        Product coffee = new Product();
        coffee.setCategory(drinks);
        coffee.setPrice(BigDecimal.valueOf(1.13));
        coffee.setTitle("coffee");
        coffee = productService.create(coffee);

        Product phone = new Product();
        phone.setCategory(smartPhone);
        phone.setPrice(BigDecimal.valueOf(124.5));
        phone.setTitle("phone");
        phone = productService.create(phone);

        Product beer = new Product();
        beer.setCategory(drinks);
        beer.setPrice(BigDecimal.valueOf(2.1));
        beer.setTitle("beer");
        beer = productService.create(beer);

        return "categories and products was created";
    }
}
