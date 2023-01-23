package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InjectController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String inject() {
        Category milkProducts = new Category();
        milkProducts.setName("Milk products");
        categoryService.add(milkProducts);
        Category drinks = new Category();
        drinks.setName("Drinks");
        categoryService.add(drinks);
        Category sweets = new Category();
        sweets.setName("Sweets");
        categoryService.add(sweets);

        Product milk = new Product();
        milk.setTitle("Milk");
        milk.setPrice(BigDecimal.valueOf(35));
        milk.setCategory(milkProducts);
        productService.add(milk);
        Product cheese = new Product();
        cheese.setTitle("Cheese");
        cheese.setPrice(BigDecimal.valueOf(420));
        cheese.setCategory(milkProducts);
        productService.add(cheese);
        Product yoghurt = new Product();
        yoghurt.setTitle("Yoghurt");
        yoghurt.setPrice(BigDecimal.valueOf(46));
        yoghurt.setCategory(milkProducts);
        productService.add(yoghurt);
        Product cola = new Product();
        cola.setTitle("Coca-Cola");
        cola.setPrice(BigDecimal.valueOf(30));
        cola.setCategory(drinks);
        productService.add(cola);
        Product sprite = new Product();
        sprite.setTitle("Sprite");
        sprite.setPrice(BigDecimal.valueOf(30));
        sprite.setCategory(drinks);
        productService.add(sprite);
        Product fanta = new Product();
        fanta.setTitle("Fanta");
        fanta.setPrice(BigDecimal.valueOf(30));
        fanta.setCategory(drinks);
        productService.add(fanta);
        Product nuts = new Product();
        nuts.setTitle("Nuts");
        nuts.setPrice(BigDecimal.valueOf(24));
        nuts.setCategory(sweets);
        productService.add(nuts);
        Product bounty = new Product();
        bounty.setTitle("Bounty");
        bounty.setPrice(BigDecimal.valueOf(25));
        bounty.setCategory(sweets);
        productService.add(bounty);
        Product kitkat = new Product();
        kitkat.setTitle("KitKat");
        kitkat.setPrice(BigDecimal.valueOf(18));
        kitkat.setCategory(sweets);
        productService.add(kitkat);

        return "putin huilo!";
    }
}
