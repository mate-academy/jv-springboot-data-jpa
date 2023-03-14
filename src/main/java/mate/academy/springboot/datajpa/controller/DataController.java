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
public class DataController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public DataController(ProductService productService,
                          CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String inject() {
        Category birds = new Category();
        birds.setName("Birds");
        final Category birdsCategory = categoryService.save(birds);
        Category cats = new Category();
        cats.setName("Cats");
        final Category catsCategory = categoryService.save(cats);

        Product pigeon = new Product();
        pigeon.setTitle("Pigeon");
        pigeon.setPrice(BigDecimal.valueOf(10L));
        pigeon.setCategory(birdsCategory);
        productService.save(pigeon);
        Product ara = new Product();
        ara.setTitle("Ara");
        ara.setPrice(BigDecimal.valueOf(1000L));
        ara.setCategory(birdsCategory);
        productService.save(ara);
        Product siamese = new Product();
        siamese.setTitle("Siamese");
        siamese.setPrice(BigDecimal.valueOf(3000L));
        siamese.setCategory(catsCategory);
        productService.save(siamese);
        Product sphinx = new Product();
        sphinx.setTitle("Sphinx");
        sphinx.setPrice(BigDecimal.valueOf(4000L));
        sphinx.setCategory(catsCategory);
        productService.save(sphinx);

        return "Deployed test data";
    }
}
