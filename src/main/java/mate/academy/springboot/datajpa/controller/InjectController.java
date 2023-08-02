package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/inject")
public class InjectController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String injectData() {
        Category phones = new Category();
        phones.setName("Phones");
        categoryService.create(phones);
        Category macBook = new Category();
        macBook.setName("Laptop");
        categoryService.create(macBook);

        Product iphoneX = new Product();
        iphoneX.setTitle("Iphone X");
        iphoneX.setPrice(BigDecimal.valueOf(999));
        iphoneX.setCategory(phones);
        productService.create(iphoneX);

        Product iphone12 = new Product();
        iphone12.setTitle("Iphone 12");
        iphone12.setPrice(BigDecimal.valueOf(1299));
        iphone12.setCategory(phones);
        productService.create(iphone12);

        Product airMax = new Product();
        airMax.setTitle("MacBook Air");
        airMax.setPrice(BigDecimal.valueOf(2100));
        airMax.setCategory(macBook);
        productService.create(airMax);
        return "Done";
    }
}
