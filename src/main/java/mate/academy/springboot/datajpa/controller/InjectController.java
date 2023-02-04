package mate.academy.springboot.datajpa.controller;

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

    public InjectController(ProductService productService,
                            CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/inject")
    public String inject() {
        Category phone = new Category();
        phone.setName("Phone");
        categoryService.save(phone);

        Category snack = new Category();
        snack.setName("Snack");
        categoryService.save(snack);

        Product iphone = new Product();
        iphone.setTitle("Iphone 14");
        iphone.setPrice(1299L);
        iphone.setCategory(phone);
        productService.save(iphone);

        Product samsung = new Product();
        samsung.setTitle("Samsung Galaxy A71");
        samsung.setPrice(359L);
        samsung.setCategory(phone);
        productService.save(samsung);

        Product bounty = new Product();
        bounty.setTitle("Bounty");
        bounty.setPrice(2L);
        bounty.setCategory(snack);
        productService.save(bounty);

        Product snickers = new Product();
        snickers.setTitle("Snickers");
        snickers.setPrice(3L);
        snickers.setCategory(snack);
        productService.save(snickers);

        return "Done!";
    }
}
