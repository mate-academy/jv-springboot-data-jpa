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
    private final ProductService productService;
    private final CategoryService categoryService;

    public InjectController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/inject")
    public void saveCategories() {
        Category categoryPhones = new Category();
        categoryPhones.setTitle("Phones");
        categoryService.save(categoryPhones);

        Category categoryTVs = new Category();
        categoryTVs.setTitle("TVs");
        categoryService.save(categoryTVs);

        Category categoryNotebooks = new Category();
        categoryNotebooks.setTitle("Notebooks");
        categoryService.save(categoryNotebooks);

        Product iphone8 = new Product();
        iphone8.setTitle("iPhone 8");
        iphone8.setCategory(categoryPhones);
        iphone8.setPrice(BigDecimal.valueOf(699));
        productService.save(iphone8);

        Product iphoneX = new Product();
        iphoneX.setTitle("iPhone X");
        iphoneX.setCategory(categoryPhones);
        iphoneX.setPrice(BigDecimal.valueOf(1099));
        productService.save(iphoneX);

        Product iphone11 = new Product();
        iphone11.setTitle("iPhone 11");
        iphone11.setCategory(categoryPhones);
        iphone11.setPrice(BigDecimal.valueOf(1199));
        productService.save(iphone11);

        Product iphone12 = new Product();
        iphone12.setTitle("iPhone 12");
        iphone12.setCategory(categoryPhones);
        iphone12.setPrice(BigDecimal.valueOf(1399));
        productService.save(iphone12);

        Product tvSamsung = new Product();
        tvSamsung.setTitle("Samsung TV 32inch");
        tvSamsung.setCategory(categoryTVs);
        tvSamsung.setPrice(BigDecimal.valueOf(499));
        productService.save(tvSamsung);

        Product tvSony = new Product();
        tvSony.setTitle("Sony TV 56inch");
        tvSony.setCategory(categoryTVs);
        tvSony.setPrice(BigDecimal.valueOf(699));
        productService.save(tvSony);

        Product nbDell = new Product();
        nbDell.setTitle("Dell inspire");
        nbDell.setCategory(categoryNotebooks);
        nbDell.setPrice(BigDecimal.valueOf(999));
        productService.save(nbDell);

        Product nbMac = new Product();
        nbMac.setTitle("Mac M1 13inc");
        nbMac.setCategory(categoryNotebooks);
        nbMac.setPrice(BigDecimal.valueOf(1499));
        productService.save(nbMac);
    }

}
