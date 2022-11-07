package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InjectController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/inject")
    public String inject() {
        Category sportCars = new Category();
        sportCars.setName("SportCars");
        sportCars = categoryService.save(sportCars);

        Category moto = new Category();
        moto.setName("Cruiser");
        moto = categoryService.save(moto);

        Product ferrari = new Product();
        ferrari.setPrice(BigDecimal.valueOf(540000));
        ferrari.setTitle("Ferrari F1");
        ferrari.setCategory(sportCars);
        ferrari = productService.save(ferrari);

        Product ducati = new Product();
        ducati.setPrice(BigDecimal.valueOf(25000));
        ducati.setTitle("Ducati");
        ducati.setCategory(moto);
        ducati = productService.save(ducati);

        Product mercedes = new Product();
        mercedes.setPrice(BigDecimal.valueOf(1500));
        mercedes.setTitle("Mercedes C63");
        mercedes.setCategory(sportCars);
        mercedes = productService.save(mercedes);

        return "Successfully injected!";
    }
}
