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
        Category noteBook = new Category();
        noteBook.setName("NOTEBOOK");
        noteBook = categoryService.save(noteBook);

        Category pc = new Category();
        pc.setName("PC");
        pc = categoryService.save(pc);

        Product lenovo = new Product();
        lenovo.setPrice(BigDecimal.valueOf(869));
        lenovo.setTitle("LENOVO");
        lenovo.setCategory(noteBook);
        lenovo = productService.save(lenovo);

        Product asus = new Product();
        asus.setPrice(BigDecimal.valueOf(670));
        asus.setTitle("ASUS");
        asus.setCategory(noteBook);
        asus = productService.save(asus);

        Product legion = new Product();
        legion.setPrice(BigDecimal.valueOf(1500));
        legion.setTitle("LEGION");
        legion.setCategory(pc);
        legion = productService.save(legion);

        return "Done!";
    }
}
