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
        Category car = new Category();
        car.setName("CAR");
        car = categoryService.save(car);
        Category truck = new Category();
        truck.setName("TRUCK");
        truck = categoryService.save(truck);

        Product bmw = new Product();
        bmw.setPrice(BigDecimal.valueOf(100));
        bmw.setTitle("bmw");
        bmw.setCategory(car);
        bmw = productService.save(bmw);
        Product audi = new Product();
        audi.setPrice(BigDecimal.valueOf(100));
        audi.setTitle("audi");
        audi.setCategory(car);
        audi = productService.save(audi);
        Product volvo = new Product();
        volvo.setPrice(BigDecimal.valueOf(100));
        volvo.setTitle("volvo");
        volvo.setCategory(truck);
        volvo = productService.save(volvo);
        return "Done!";
    }
}
