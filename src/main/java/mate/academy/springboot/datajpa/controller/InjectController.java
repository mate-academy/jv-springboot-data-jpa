package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inject")
public class InjectController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String injectData() {
        // save categories
        Category phone = new Category();
        phone.setName("Phone");
        Category laptop = new Category();
        laptop.setName("Laptop");
        Category tv = new Category();
        tv.setName("TV");

        categoryService.save(phone);
        categoryService.save(laptop);
        categoryService.save(tv);

        // save products
        Product iphone = new Product();
        iphone.setTitle("iPhone 7");
        iphone.setCategory(phone);
        iphone.setPrice(BigDecimal.valueOf(499));

        Product samsungLaptop = new Product();
        samsungLaptop.setTitle("Samsung Galaxy Book3");
        samsungLaptop.setCategory(laptop);
        samsungLaptop.setPrice(BigDecimal.valueOf(500));

        Product samsungTV = new Product();
        samsungTV.setTitle("Samsung Crystal UHD");
        samsungTV.setCategory(tv);
        samsungTV.setPrice(BigDecimal.valueOf(695));

        productService.save(iphone);
        productService.save(samsungLaptop);
        productService.save(samsungTV);
        return "Done!";
    }
}
